package com.nginx;

/**
 * @Author suosong
 * @Date 2018/8/18

 源码安装nginx
    wget http://nginx.org/download/nginx-1.12.2.tar.gz     可以把其放在/opt/download下面
    然后解压缩安装
    安装完毕后，去对应的目录下面，./nginx -v   如果出来了版本信息，那么就是成功了

 启动nginx:
    ./nginx

 重启nginx:
    ./nginx -s reload



 nginx.conf配置

    user  nobody;                                       //启动用户
    worker_processes  1;                                //进程数，一般跟cpu的数目保持一致

    error_log  logs/error.log;                          //错误日志目录
    #error_log  logs/error.log  notice;
    #error_log  logs/error.log  info;

    pid        logs/nginx.pid;                          //启动进程id


    events {
        worker_connections  1024;                       //最大连接数，如果有4个worker,每个是256个连接，那么最大有1024个连接
    }


    http {
        include       mime.types;                       //包含配置文件，nginx启动时，只读取这一个文件，其他文件都是用include包含进来
        default_type  application/octet-stream;

        #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '       //将nginx的内置变量组合起来，变成一种日志格式
        #                  '$status $body_bytes_sent "$http_referer" '
        #                  '"$http_user_agent" "$http_x_forwarded_for"';

        #access_log  logs/access.log  main;                  //访问日志

        sendfile        on;
        #tcp_nopush     on;

        #keepalive_timeout  0;
        keepalive_timeout  65;                                //超时时间，单位是秒

        #gzip  on;

        server {                                   //虚拟主机，只所以叫这个名字，是因为在客户端浏览器看来，是一台主机。可以有多个虚拟主机。相当于起来了多个tomcat。就是一个nginx可以监听n个端口
            listen       80;
            server_name  localhost;

            #charset koi8-r;

            #access_log  logs/host.access.log  main;                 //server 访问日志,main表示log_format的名字

            location / {                                             //默认访问的路径，
                root   html;                                        //root是根的意思   后面的路径都是相对于nginx的安装路径
                index  index.html index.htm;                         //index是指如果不敲路径，访问哪个页面（欢迎页面）。如果不存在index.html的话，就访问index.htm
            }

            #error_page  404              /404.html;                       //出现404后所访问的页面

            # redirect server error pages to the static page /50x.html
            #
            error_page   500 502 503 504  /50x.html;                        //出现50x 后所访问的页面
            location = /50x.html {
                root   html;                                                //如果location定义了文件
            }

            # proxy the PHP scripts to Apache listening on 127.0.0.1:80
            #
            #location ~ \.php$ {
            #    proxy_pass   http://127.0.0.1;
            #}

            # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
            #
            #location ~ \.php$ {
            #    root           html;
            #    fastcgi_pass   127.0.0.1:9000;
            #    fastcgi_index  index.php;
            #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
            #    include        fastcgi_params;
            #}

            # deny access to .htaccess files, if Apache's document root
            # concurs with nginx's one
            #
            #location ~ /\.ht {
            #    deny  all;
            #}
        }


        # another virtual host using mix of IP-, name-, and port-based configuration
        #
        #server {
        #    listen       8000;
        #    listen       somename:8080;
        #    server_name  somename  alias  another.alias;

        #    location / {
        #        root   html;
        #        index  index.html index.htm;
        #    }
        #}


        # HTTPS server
        #
        #server {
        #    listen       443 ssl;
        #    server_name  localhost;

        #    ssl_certificate      cert.pem;
        #    ssl_certificate_key  cert.key;

        #    ssl_session_cache    shared:SSL:1m;
        #    ssl_session_timeout  5m;

        #    ssl_ciphers  HIGH:!aNULL:!MD5;
        #    ssl_prefer_server_ciphers  on;

        #    location / {
        #        root   html;
        #        index  index.html index.htm;
        #    }
        #}

    }

 */
public class Nginx {
}
