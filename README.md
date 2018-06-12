0、报不能加载驱动
    需要手动导入sqlserver驱动包lib
1、Apache2.4 安装
	
	1.1 ServerRoot改为本地apache的安装目录
	1.2 Listen 80 因为IIS占用的端口也是80端口 所以可以改为其他的端口例如：9090
	1.3 httpd.exe -k install -n Apache   将apache注册为服务
2、Apache2.4 配置

	2.1 反向代理配置：
		httpd.conf 文件（三处打开注释）
		
		LoadModule proxy_module modules/mod_proxy.so
		LoadModule proxy_http_module modules/mod_proxy_http.so
		Include conf/extra/httpd-vhosts.conf
		
		extra/httpd-vhosts.conf 文件
		<VirtualHost *:9090>
			ServerAdmin webmaster@host.example.com
			DocumentRoot "D:\project\framework\MtnoWeb_SFGL\WebContent"
			ServerName fxTest
			ErrorLog "logs/fxTestLog"
			TransferLog "logs/fxTestLogTransfer"
			ProxyPreserveHost On 
			ProxyPass / http://192.168.4.50:8080/MtnoWeb_LineCollect_YN/
			ProxyPassReverse / http://192.168.4.50:8080/MtnoWeb_LineCollect_YN/
		</VirtualHost>
		
3、Apache负载均衡

	HTTP方式：
	AJP方式：
		两个tomcat的seerver.xml文件 加上jvmRoute参数
			<Engine name="Catalina" defaultHost="localhost" jvmRoute="tomcat1">
			<Engine name="Catalina" defaultHost="localhost" jvmRoute="tomcat2">
		httpd.conf 文件（三处打开注释）
			LoadModule proxy_module modules/mod_proxy.so 
			LoadModule proxy_ajp_module modules/mod_proxy_ajp.so 
			LoadModule proxy_balancer_module modules/mod_proxy_balancer.so 
			LoadModule proxy_connect_module modules/mod_proxy_connect.so 
			LoadModule proxy_ftp_module modules/mod_proxy_ftp.so 
			LoadModule proxy_http_module modules/mod_proxy_http.so 
			LoadModule slotmem_shm_module modules/mod_slotmem_shm.so 
			LoadModule lbmethod_byrequests_module modules/mod_lbmethod_byrequests.so
			
			
			#balancer://mycluster 对应extra/httpd-vhosts.conf 文件下的配置
			# ajp后面的分别是ip 端口 和 jvmRoute参数
			ProxyRequests Off 
			<Proxy balancer://mycluster>  
				BalancerMember ajp://192.168.4.50:8009/ loadfactor=1 route=tomcat1
				BalancerMember ajp://192.168.4.50:8010/ loadfactor=1 route=tomcat2
			</Proxy> 

		extra/httpd-vhosts.conf 文件
			<VirtualHost *:9091> 
				ServerName 437865981@qq.com
				ServerAlias localhost
				ProxyPass / balancer://mycluster/ stickysession=jsessionid nofailover=On
				ProxyPassReverse / balancer://mycluster/
				ErrorLog "logs/lbtest-error.log"
				CustomLog "logs/lbtest-access.log" common
			</VirtualHost>
			
	
4、tomcat乱码：

	1、server.xml （解决浏览器get请求传给后台乱码）
		URIEncoding = "UTF-8"  
	2、Catalina.bat （解决后台返回浏览器显示乱码）
		set JAVA_OPTS=%JAVA_OPTS% %LOGGING_CONFIG% -Dfile.encoding=UTF-8
	3、IDEA 添加VM_Options   -Dfile.encoding=UTF-8
		
5、Nginx 安装与部署

	1、 启动失败的原因：
		1.1 监听的端口被占用，例如8080 80等
		1.2 Nginx的目录中包含中文导致找不到Nginx.conf文件
	2、配置详情（https://www.cnblogs.com/Chiler/p/8027167.html）
		2.1 worker_processes  
			和cpu有关 一般和CPU核数相同
		
		2.2events {
			#nginx最大负载量 
	　　　　worker_connections 1024;
		}
		
		2.3include      mime.types;
	3、反向代理
		3.1 在HTTP节点下配置server节点
		server
			{
				listen 92;
				server_name tomcat1.com;   #需要再host文件下配置域名解析
				location / {
					proxy_redirect off;
					proxy_set_header Host $host;
					proxy_set_header X-Real-IP $remote_addr;
					proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
					proxy_pass http://192.168.4.4:8081;
				}
				access_log logs/tomcat2.tk_access.log;
			}
	4、负载均衡（https://www.cnblogs.com/microtiger/p/7623858.html）
		upstream四种方式：
		1、轮询（默认）：每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器down掉，能自动剔除。 
		2、比重（weight）：指定轮询几率，weight和访问比率成正比，用于后端服务器性能不均的情况。
		3、ip_hash：每个请求按访问ip的hash结果分配，这样每个访客固定访问一个后端服务器，可以解决session的问题。
		4、第三方（fair、url_hash）
	5、Nginx常见问题：
		5.1：Session共享的问题
			1、不使用session用cookies
			2、利用redis缓存保存session等
			3、利用nginx的ip_hash nginx中的ip_hash技术能够将某个ip的请求定向到同一台后端，这样一来这个ip下的某个客户端和某个后端就能建立起稳固的session
				注意这种方法如果Nginx不是最前端，假如前端还有squid代理服务器，或者Nginx不直接指向后端，后端又做了其他负载均衡，导致得到的ip和转发的ip都不是真实的
				那么某个客户端的请求肯定不能定位到同一台session应用服务器上
				可利用
6、国际化（三种方式）

    6、1 基于浏览器请求的国际化实现
     根据浏览器的语言设置，自动切换语言
    6.2 基于Session的国际化
    6.3 基于Cookies的国际化
    三者的基本原理一一致：
    RequestContext requestContext = new RequestContext(request);
    可以从request中获取到国际化信息
    Locale locale = new Locale("zh", "CN");
    后两种在这之前将国际化信息Local放入到session或者cookies中
    
    
	
    