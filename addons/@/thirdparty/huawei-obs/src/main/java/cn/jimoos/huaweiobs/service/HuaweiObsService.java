package cn.jimoos.huaweiobs.service;

import cn.jimoos.huaweiobs.config.HuaweiObsProperties;
import cn.jimoos.huaweiobs.form.ObsTemporarySignForm;
import cn.jimoos.huaweiobs.vo.ObsTemporarySignVO;
import cn.jimoos.huaweiobs.vo.TemporaryAccessKeyVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huaweicloud.sdk.core.auth.GlobalCredentials;
import com.huaweicloud.sdk.core.auth.ICredential;
import com.huaweicloud.sdk.core.exception.ConnectionException;
import com.huaweicloud.sdk.core.exception.RequestTimeoutException;
import com.huaweicloud.sdk.core.exception.ServiceResponseException;
import com.huaweicloud.sdk.iam.v3.IamClient;
import com.huaweicloud.sdk.iam.v3.model.*;
import com.huaweicloud.sdk.iam.v3.region.IamRegion;
import com.obs.services.ObsClient;
import com.obs.services.model.PostSignatureRequest;
import com.obs.services.model.PostSignatureResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class HuaweiObsService {

    @Resource
    HuaweiObsProperties huaweiObsProperties;


    public List<ObsTemporarySignVO> getTemporarySignature(ObsTemporarySignForm form) {
        List<ObsTemporarySignForm.Blob> blobs = form.getBlobs();
        if (blobs.isEmpty()) {
            throw new IllegalArgumentException("请上传文件信息!");
        }
        // 创建ObsClient实例
        String endPoint = this.huaweiObsProperties.getEndPoint();

        Assert.notNull(endPoint, "Huawei Obs未配置，请配置后重试");

        try (ObsClient obsClient = new ObsClient(this.huaweiObsProperties.getAccessKey(), this.huaweiObsProperties.getSecretKey(), endPoint)) {
            List<ObsTemporarySignVO> result = new ArrayList<>();
            for (ObsTemporarySignForm.Blob blob : blobs) {
                String bucketName = this.huaweiObsProperties.getBucketNameByType(blob.getType());
                long nanoTime = System.currentTimeMillis();
                String blobName = nanoTime + "_" + blob.getName();
                // 替换成您对应的操作
                Long duration = this.huaweiObsProperties.getDuration();

                PostSignatureRequest postSignatureRequest = new PostSignatureRequest(duration, bucketName, blobName);
                // 设置表单参数
                Map<String, Object> formParams = new HashMap<>();
                // 设置对象访问权限为公共读
                formParams.put("x-obs-acl", "public-read");


                postSignatureRequest.setFormParams(formParams);

                PostSignatureResponse postSignature = obsClient.createPostSignature(postSignatureRequest);

                ObsTemporarySignVO obsTemporarySignVO = new ObsTemporarySignVO();
                String url = bucketName + "." + endPoint;
                obsTemporarySignVO.setUrl(url);
                obsTemporarySignVO.setBlobUrl(url + "/" + blobName);
                obsTemporarySignVO.setExpireAt(postSignature.getExpiration());
                obsTemporarySignVO.setPolicy(postSignature.getPolicy());
                obsTemporarySignVO.setAccessKeyId(this.huaweiObsProperties.getAccessKey());
                obsTemporarySignVO.setSignature(postSignature.getSignature());
                obsTemporarySignVO.setXObsAcl("public-read");
                obsTemporarySignVO.setKey(blobName);
                result.add(obsTemporarySignVO);
            }
            return result;
        } catch (IOException e) {
            log.error("Exception:", e);
        }
        return new ArrayList<>();
    }

    public TemporaryAccessKeyVO getTemporaryAccessKey(int type) {
        ICredential auth = new GlobalCredentials()
                .withAk(huaweiObsProperties.getAccessKey())
                .withSk(huaweiObsProperties.getSecretKey());

        IamClient client = IamClient.newBuilder()
                .withCredential(auth)
                .withRegion(IamRegion.CN_EAST_3)
                .build();
        CreateTemporaryAccessKeyByTokenRequest request = new CreateTemporaryAccessKeyByTokenRequest();
        CreateTemporaryAccessKeyByTokenRequestBody body = new CreateTemporaryAccessKeyByTokenRequestBody();
        ServicePolicy policyIdentity = new ServicePolicy();
        policyIdentity.withVersion("1.1");
        ServiceStatement serviceStatement = new ServiceStatement();
        serviceStatement.addActionItem("obs:object:*");
        serviceStatement.addResourceItem("obs:*:*:object:*");
        Map<String, Map<String, List<String>>> condition = Maps.newHashMap();
        Map<String, List<String>> stringEquals = new HashMap<>();
        stringEquals.put("obs:prefix", Lists.newArrayList("public"));
        condition.put("StringEquals", stringEquals);
        serviceStatement.withCondition(condition);
        serviceStatement.setEffect(ServiceStatement.EffectEnum.ALLOW);
        policyIdentity.setStatement(Lists.newArrayList(serviceStatement));
        IdentityToken tokenIdentity = new IdentityToken();
        tokenIdentity.withDurationSeconds(86400);
        List<TokenAuthIdentity.MethodsEnum> listIdentityMethods = new ArrayList<>();
        listIdentityMethods.add(TokenAuthIdentity.MethodsEnum.fromValue("token"));
        TokenAuthIdentity identityAuth = new TokenAuthIdentity();
        identityAuth.withMethods(listIdentityMethods)
                .withToken(tokenIdentity)
                .withPolicy(policyIdentity);
        TokenAuth authbody = new TokenAuth();
        authbody.withIdentity(identityAuth);
        body.withAuth(authbody);
        request.withBody(body);
        try {
            CreateTemporaryAccessKeyByTokenResponse response = client.createTemporaryAccessKeyByToken(request);
            Credential credential = response.getCredential();
            TemporaryAccessKeyVO temporaryAccessKeyVO = new TemporaryAccessKeyVO();
            temporaryAccessKeyVO.setExpiresAt(credential.getExpiresAt());
            temporaryAccessKeyVO.setAccess(credential.getAccess());
            temporaryAccessKeyVO.setSecret(credential.getSecret());
            temporaryAccessKeyVO.setSecuritytoken(credential.getSecuritytoken());
            temporaryAccessKeyVO.setBucketName(huaweiObsProperties.getBucketNameByType(type));
            temporaryAccessKeyVO.setEndpoint(huaweiObsProperties.getEndPoint());
            return temporaryAccessKeyVO;
        } catch (ConnectionException | RequestTimeoutException e) {
            log.error("ConnectionException||RequestTimeoutException:", e);
        } catch (ServiceResponseException e) {
            log.error("ServiceResponseException:", e);
        }
        return null;
    }

}
