package com.hcwl.uc.sdk.core.service;

import com.hcwl.uc.sdk.core.service.dto.Device;
import com.hcwl.uc.sdk.core.service.dto.Project;
import com.hcwl.uc.sdk.core.exception.UCSdkException;
import com.hcwl.uc.sdk.core.service.dto.Page;
import com.hcwl.uc.sdk.core.service.param.GetAllProjectsParams;
import com.hcwl.uc.sdk.core.service.param.GetProjectCountParams;
import com.hcwl.uc.sdk.core.service.param.GetProjectsParams;

import java.util.List;

/**
 * 此类定义了项目相关的方法.
 * @author zhangdx
 * @date 2022-06-02
 */
public interface IProjectService {

     /**
      * 获取项目列表.
      * @param params
      * @return
      */
     Page<Project> getProjects(GetProjectsParams params) throws UCSdkException;

     /**
      * 获取项目列表(不分页).
      * @param params
      * @return
      */
     List<Project> getAllProjects(GetAllProjectsParams params) throws UCSdkException;

     /**
      * 获取项目数.
      * @param params
      * @return
      */
     Long getProjectCount(GetProjectCountParams params) throws UCSdkException;

     /**
      * 添加项目.
      * @param project
      * @return 新建项目的ID
      */
     Long addProject(Project project) throws UCSdkException;

     /**
      * 修改项目.
      * @param project
      * @throws UCSdkException
      */
     void updateProject(Project project) throws UCSdkException;

     /**
      * 删除项目.
      * @param projectId
      * @throws UCSdkException
      */
     void deleteProject(Long projectId) throws UCSdkException;

     /**
      * 添加设备
      */
     void addDevice(Device device) throws UCSdkException;

     /**
      * 修改设备
      */
     void updateDevice(Device device) throws UCSdkException;

     /**
      * 删除设备
      */
     void deleteDevice(Long deviceId) throws UCSdkException;

}
