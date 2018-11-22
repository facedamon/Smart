package com.facedamon.smart.system;

import com.facedamon.smart.system.domain.Menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: 树形结构工具类
 * @Author: facedamon
 * @CreateDate: 2018/9/30 下午9:12
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/9/30 下午9:12
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class TreeUtils {

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public static List<Menu> getChildPerms(List<Menu> list, int parentId) {
        List<Menu> returnList = new ArrayList<>();
        for (Iterator<Menu> iterator = list.iterator(); iterator.hasNext(); ) {
            Menu t = iterator.next();
            /**
             * 根据传入的某个父节点ID，遍历该父节点的所有子节点
             */
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表 填充menu 的childList
     *
     * @param list
     * @param menu
     */
    private static void recursionFn(List<Menu> list, Menu menu) {
        List<Menu> childList = getChildList(list, menu);
        menu.setChildren(childList);

        /**
         * 递归遍历子列表
         */
        for (Menu tChild : childList) {
            if (hasChild(list, tChild)) {
                Iterator<Menu> it = childList.iterator();
                while (it.hasNext()) {
                    recursionFn(list, it.next());
                }
            }
        }
    }

    private static boolean hasChild(List<Menu> list, Menu tChild) {
        return getChildList(list, tChild).size() > 0 ? true : false;
    }

    private static List<Menu> getChildList(List<Menu> list, Menu menu) {
        List<Menu> tlist = new ArrayList<>();
        for (Iterator<Menu> it = list.iterator(); it.hasNext(); ) {
            Menu n = it.next();
            if (n.getParentId().longValue() == menu.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }
}