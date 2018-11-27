package br.com.softplan.desafio.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("desafio")
public class DesafioManagementApiProperty {
    
    private String originPermitida = "http://localhost:4200";
    
    private final Seguranca seguranca = new Seguranca();
    
    private final Mail mail = new Mail();
    
    private final S3 s3 = new S3();
    
    public S3 getS3() {
        return this.s3;
    }
    
    public Mail getMail() {
        return this.mail;
    }
    
    public Seguranca getSeguranca() {
        return this.seguranca;
    }
    
    public String getOriginPermitida() {
        return this.originPermitida;
    }
    
    public void setOriginPermitida(final String originPermitida) {
        this.originPermitida = originPermitida;
    }
    
    public static class S3 {
        
        private String accessKeyId;
        
        private String secretAccessKey;
        
        private String bucket = "desafio-sofplan";
        
        public String getBucket() {
            return this.bucket;
        }
        
        public void setBucket(final String bucket) {
            this.bucket = bucket;
        }
        
        public String getAccessKeyId() {
            return this.accessKeyId;
        }
        
        public void setAccessKeyId(final String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }
        
        public String getSecretAccessKey() {
            return this.secretAccessKey;
        }
        
        public void setSecretAccessKey(final String secretAccessKey) {
            this.secretAccessKey = secretAccessKey;
        }
    }
    
    public static class Seguranca {
        
        private boolean enableHttps;
        
        public boolean isEnableHttps() {
            return this.enableHttps;
        }
        
        public void setEnableHttps(final boolean enableHttps) {
            this.enableHttps = enableHttps;
        }
    }
    
    public static class Mail {
        
        private String host;
        
        private Integer port;
        
        private String username;
        
        private String password;
        
        public String getHost() {
            return this.host;
        }
        
        public void setHost(final String host) {
            this.host = host;
        }
        
        public Integer getPort() {
            return this.port;
        }
        
        public void setPort(final Integer port) {
            this.port = port;
        }
        
        public String getUsername() {
            return this.username;
        }
        
        public void setUsername(final String username) {
            this.username = username;
        }
        
        public String getPassword() {
            return this.password;
        }
        
        public void setPassword(final String password) {
            this.password = password;
        }
    }
}
