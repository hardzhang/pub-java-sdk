package com.hcwl.uc.sdk.core.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.hcwl.uc.sdk.core.config.UcSdkConfigurationProperties;
import com.hcwl.uc.sdk.core.exception.UCSdkException;
import com.hcwl.uc.sdk.core.service.IProjectService;
import com.hcwl.uc.sdk.core.service.dto.Device;
import com.hcwl.uc.sdk.core.service.dto.Districts;
import com.hcwl.uc.sdk.core.service.dto.Page;
import com.hcwl.uc.sdk.core.service.dto.Project;
import com.hcwl.uc.sdk.core.service.param.GetAllProjectsParams;
import com.hcwl.uc.sdk.core.service.param.GetProjectCountParams;
import com.hcwl.uc.sdk.core.service.param.GetProjectsParams;
import com.hcwl.uc.sdk.core.utils.UcAdminRestApiClient;
import com.hcwl.uc.sdk.core.utils.UcClientRestApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author luoxy
 * @date 2022-06-15
 */
@Service("ucProjectService")
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private UcSdkConfigurationProperties ucSdkProperties;

    public Page<Project> getProjects(GetProjectsParams params) throws UCSdkException {
        String host = ucSdkProperties.getUri();
        String url = host + "/auth/assets/projects/page-project-sdk?applicationId={applicationId}&userId={userId}&tenantId={tenantId}&projectName={projectName}&projectType={projectType}&projectStatus={projectStatus}&province={province}&city={city}&county={county}&offset={offset}&limit={limit}";
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("applicationId", params.getApplicationId());
        map.put("userId", params.getAccountId());
        map.put("tenantId", params.getOrgId());
        map.put("projectName", params.getName());
        map.put("projectType", params.getType());
        map.put("projectStatus", params.getStatus());
        Districts districts = params.getDistricts();
        map.put("province", districts != null ? districts.getProvince() : null);
        map.put("city", districts != null ? districts.getCity() : null);
        map.put("county", districts != null ? districts.getCounty() : null);
        map.put("offset", params.getOffset());
        map.put("limit", params.getLimit());
        return UcAdminRestApiClient.get(url, map,new UcClientRestApiClient.ResultType(new TypeReference<Page<Project>>(){}));
    }

    public List<Project> getAllProjects(GetAllProjectsParams params) throws UCSdkException {
        List<Project> result = new ArrayList<Project>();
        long offset = 0;
        long limit = 100;
        while (true) {
            GetProjectsParams getProjectsParams = new GetProjectsParams();
            getProjectsParams.setApplicationId(params.getApplicationId());
            getProjectsParams.setAccountId(params.getAccountId());
            getProjectsParams.setOffset(offset);
            getProjectsParams.setLimit(limit);
            Page<Project> page = getProjects(getProjectsParams);
            long count = page.getElements()!=null?page.getElements().size():0;
            if (count == 0) {
                break;
            }
            result.addAll(page.getElements());
            offset = offset + limit;
        }
        return result;
    }

    public Long getProjectCount(GetProjectCountParams params) throws UCSdkException {
        String host = ucSdkProperties.getUri();
        String url = host + "/auth/assets/projects/count-project-sdk?applicationId={applicationId}&userId={userId}&tenantId={tenantId}";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("applicationId", params.getApplicationId());
        map.put("userId", params.getAccountId());
        map.put("tenantId", params.getOrgId());
        return UcAdminRestApiClient.get(url, map,new UcClientRestApiClient.ResultType(Long.class));
    }

    public Long addProject(Project project) throws UCSdkException {
        String url = "/uc/client/v1.0/projects";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", project.getName());
        map.put("type", project.getType());
        map.put("status", project.getStatus());
        map.put("biz_id", project.getBizId());
        Districts districts = project.getDistricts();
        if (districts != null) {
            Map<String, Object> districtsMap = new HashMap<String, Object>();
            districtsMap.put("province", districts.getProvince());
            districtsMap.put("city", districts.getCity());
            districtsMap.put("county", districts.getCounty());
            map.put("districts", districtsMap);
        }
        return UcClientRestApiClient.post(url, map, new UcClientRestApiClient.ResultType(Long.class),true);
    }

    public void updateProject(Project project) throws UCSdkException {
        String url = "/uc/client/v1.0/projects/{projectId}";
        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("projectId", project.getId());
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("name", project.getName());
        body.put("type", project.getType());
        body.put("status", project.getStatus());
        body.put("biz_id", project.getBizId());
        Districts districts = project.getDistricts();
        if (districts != null) {
            Map<String, Object> districtsMap = new HashMap<String, Object>();
            districtsMap.put("province", districts.getProvince());
            districtsMap.put("city", districts.getCity());
            districtsMap.put("county", districts.getCounty());
            body.put("districts", districtsMap);
        }
        UcClientRestApiClient.put(url, body, urlVariables,null,true);
    }

    public void deleteProject(Long projectId) throws UCSdkException {
        String url = "/uc/client/v1.0/projects/{projectId}";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectId", projectId);
        UcClientRestApiClient.delete(url, map,null,true);
    }

    public void addDevice(Device device) throws UCSdkException {
        throw new UnsupportedOperationException("此方法还未实现");
    }

    public void updateDevice(Device device) throws UCSdkException {
        throw new UnsupportedOperationException("此方法还未实现");
    }

    public void deleteDevice(Long deviceId) throws UCSdkException {
        throw new UnsupportedOperationException("此方法还未实现");
    }
}
