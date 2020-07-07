package xm.project.p4.sp;

import lombok.Data;

import java.util.List;

@Data
public class JqGridModel<T> {
    // 页面下标
    private Integer page;

    // 总的数量
    private Integer total;

    // 数据
    private List<T> rows;
}
