package com.hydp.service;

import com.hydp.dto.OrderPaymentDTO;
import com.hydp.dto.Result;
import com.hydp.entity.VoucherOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IVoucherOrderService extends IService<VoucherOrder> {

    Result seckillVoucher(Long voucherId, int buyNumber);

    void createVoucherOrder(VoucherOrder voucherOrder);

    Result payment(OrderPaymentDTO orderPaymentDTO);

    Result commonVoucher(Long voucherId, int buyNumber);

    Result limitVoucher(Long voucherId, int buyNumber);
    Result limitVoucher1(Long voucherId, int buyNumber);
    Result limitVoucher2(Long voucherId, int buyNumber);
}
