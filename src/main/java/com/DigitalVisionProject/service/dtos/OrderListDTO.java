package com.DigitalVisionProject.service.dtos;

import java.util.List;

public class OrderListDTO {

    private List<OrderDTO> oderList;
    private TotalDTO total;

    public OrderListDTO(List<OrderDTO> oderList, TotalDTO total) {
        this.oderList = oderList;
        this.total = total;
    }

    public List<OrderDTO> getOderList() {
        return oderList;
    }

    public void setOderList(List<OrderDTO> oderList) {
        this.oderList = oderList;
    }

    public TotalDTO getTotal() {
        return total;
    }

    public void setTotal(TotalDTO total) {
        this.total = total;
    }
}
