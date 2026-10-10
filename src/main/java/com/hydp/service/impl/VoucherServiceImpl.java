package com.hydp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hydp.dto.Result;
import com.hydp.entity.SeckillVoucher;
import com.hydp.entity.Voucher;
import com.hydp.mapper.VoucherMapper;
import com.hydp.service.ISeckillVoucherService;
import com.hydp.service.IVoucherService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hydp.utils.RedisConstants.SECKILL_STOCK_KEY;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class VoucherServiceImpl extends ServiceImpl<VoucherMapper, Voucher> implements IVoucherService {

    @Resource
    private ISeckillVoucherService seckillVoucherService;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Result queryVoucherOfShop(Long shopId) {
        // 查询优惠券信息
        List<Voucher> vouchers = getBaseMapper().queryVoucherOfShop(shopId);
        // 返回结果
        return Result.ok(vouchers);
    }

    @Override
    @Transactional
    public void addSeckillVoucher(Voucher voucher) {
        // 保存优惠券
        save(voucher);
        // 保存秒杀信息
        SeckillVoucher seckillVoucher = new SeckillVoucher();
        seckillVoucher.setVoucherId(voucher.getId());
        seckillVoucher.setStock(voucher.getStock());
        seckillVoucher.setBeginTime(voucher.getBeginTime());
        seckillVoucher.setEndTime(voucher.getEndTime());
        seckillVoucherService.save(seckillVoucher);

        // 默认时区
        ZoneId zoneId = ZoneId.systemDefault();

        // 保存秒杀库存到Redis中
        Map<String, Object> voucherMap = new HashMap<>();
        voucherMap.put("id", voucher.getId());
        voucherMap.put("stock", voucher.getStock());
        voucherMap.put("beginTime", voucher.getBeginTime().atZone(zoneId).toInstant().toEpochMilli());  // 转换为时间戳（以毫秒为单位）
        voucherMap.put("endTime", voucher.getEndTime().atZone(zoneId).toInstant().toEpochMilli());  // 转换为时间戳（以毫秒为单位）
        redisTemplate.opsForHash().putAll(SECKILL_STOCK_KEY + voucher.getId(),voucherMap);
    }
}
