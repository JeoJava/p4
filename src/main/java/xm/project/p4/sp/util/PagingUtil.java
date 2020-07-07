package xm.project.p4.sp.util;

// 分页工具
public class PagingUtil {
    // 计算分页数量
    public static int totalPageCount(int totalCount, int pageSize) {
        return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
    }

    // 验证分页属性page和size的合法性
    public static void checkPageAndSize(int page, int size) {
        if (page < 0) {
            throw new IllegalArgumentException("page is not allowed to be less than zero, page: " + page);
        }
        if (size < 0) {
            throw new IllegalArgumentException("size is not allowed to be less than zero, size: " + size);
        }
    }
}
