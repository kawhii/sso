/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.shiro.client.demo.core.github;

import com.carl.auth.shiro.client.demo.core.PrincipalBindResolver;

import java.util.List;

/**
 * github内存id取决器
 *
 * @author Carl
 * @date 2017/10/8
 * @since 1.5.0
 */
public class GitHubMemoryPrincipalBindResolver implements PrincipalBindResolver {
    private List<String> idStorage;

    public GitHubMemoryPrincipalBindResolver(List<String> idStorage) {
        this.idStorage = idStorage;
    }

    public List<String> getIdStorage() {
        return idStorage;
    }

    public void setIdStorage(List<String> idStorage) {
        this.idStorage = idStorage;
    }

    @Override
    public boolean isBind(String id) {
        return this.idStorage.contains(id);
    }
}
