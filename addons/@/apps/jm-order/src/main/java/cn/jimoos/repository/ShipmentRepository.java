package cn.jimoos.repository;

import cn.jimoos.dao.ShipmentMapper;
import cn.jimoos.entity.ShipmentEntity;
import cn.jimoos.model.Shipment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author keepcleargas
 * @date 2021/04/08 15:15
 */
@Repository
@Slf4j
public class ShipmentRepository {
    @Resource
    ShipmentMapper shipmentMapper;


    public void save(ShipmentEntity shipmentEntity) {
        if (shipmentEntity.getId() == null) {
            shipmentMapper.insert(shipmentEntity);
        } else {
            shipmentMapper.updateByPrimaryKey(shipmentEntity);
        }
    }

    public ShipmentEntity byUnionId(Integer type, String outTradeNo) {
        return wrapper(shipmentMapper.findOneByTypeAndOutTradeNo(type, outTradeNo));
    }

    /**
     * shipment的 entity wrapper方法
     *
     * @param shipment shipment object
     * @return ShipmentEntity
     */
    private ShipmentEntity wrapper(Shipment shipment) {
        if (shipment != null) {
            ShipmentEntity shipmentEntity = new ShipmentEntity();

            BeanUtils.copyProperties(shipment, shipmentEntity);
            return shipmentEntity;
        }
        return null;
    }
}
