# mpush-boot

## Main

- ServerLauncher
```
......
laucher.init(); 
laucher.start();
addHook(laucher);
......
```
- Shutdown Hook

优雅地停止服务
```
addHook(ServerLauncher launcher) {
    Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    ......
                }, "mpush-shutdown-hook-thread")
        );
}
```
## ServerLaucher
- 初始化
```
init(){
    chain = BootChain.chain();
    serverEventListener = ServerEventListenerFactory.create();
    
    serverEventListener.init(mPushServer);
    chain.boot().setNext(...)...end();  
}
```
- 开始
```
start(){
    chain.start();
}
```
- 结束
```
stop(){
    chain.stop();
}
```
## BootChain

- 实例化
```
public static BootChain chain() {
        return new BootChain();
    }
```
- 初始化、服务监听
```
private final BootJob boot = new BootJob() {
        {
            ServerEventListenerFactory.create();// 初始化服务监听
        }

        @Override
        protected void start() {
            ......
            startNext();
        }

        @Override
        protected void stop() {
            stopNext();
            ......
        }
    };
    
private BootJob last = boot;
 
    
public BootChain boot() {
        return this;
    }
    
public void end() {
        setNext(new BootJob() {
            @Override
            protected void start() {
                EventBus.post(new ServerStartupEvent());
                ......
            }

            @Override
            protected void stop() {
                ......
                EventBus.post(new ServerShutdownEvent());
            }

        });
    }  
```
- 链
```
public BootChain setNext(BootJob bootJob) {
        this.last = last.setNext(bootJob);
        return this;
    }

public BootChain setNext(Supplier<BootJob> next, boolean enabled) {
        if (enabled) {
            return setNext(next.get());
        }
        return this;
    } 
```
enable选项支持配置
## BootJob

抽象类
```
protected BootJob next;

protected abstract void start();

protected abstract void stop();
```
可以优化写法将startNext()等写到统一位置，但是需要考虑异步成功场景

mpush-tools 配置方法类  com.mpush.tools.config.CC 
## CacheManagerBoot

缓存模块

开启
```
CacheManagerFactory.create().init();
```
停止
```
CacheManagerFactory.create().destroy();
```
涉及到mpush-spi中CacheManagerFactory和CacheManager

具体实现在mpush-cache中
## ServiceRegistryBoot和ServiceDiscoveryBoot 

服务注册与发现

开启
```
ServiceRegistryFactory.create().syncStart();
```
停止
```
ServiceRegistryFactory.create().syncStop();
```
涉及到mpush-spi中ServiceDiscoveryFactory和ServiceRegistryFactory

具体实现在mpush-zk中

## RouterCenterBoot

路由中心 RouterCenter

开启
```
mPushServer.getRouterCenter().start();
```
路由中心RouterCenter在MPushServer中
```
public MPushServer() {
    ......
    routerCenter = new RouterCenter(this);
    ......
    }
```
具体实现在mpush-core中com.mpush.core.router
## PushCenterBoot

推送中心 PushCenter

类似上述RouterCenterBoot

具体实现在mpush-core中com.mpush.core.push
## HttpProxyBoot

Http代理

开启
```
mPushServer.getHttpClient().syncStart();
DnsMappingManager.create().start();
```
HttpClient → NettyHttpClient（位于mpush-netty）

涉及到mpush-spi中的DnsMapping和DnsMappingFactory

具体实现在mpush-common中 com.mpush.common.net
## MonitorBoot

监控服务 MonitorService

类似上述RouterCenterBoot

具体实现在mpush-monitor中
## ServerBoot

注册发现相关的服务，包括以下服务：

1. 接入服务 ConnectionServer - ConnServerNode

2. Websocket接入服务 WebsocketServer - WebsocketServerNode

3. Udp网关服务 UdpGatewayServer - UdpGatewayServerNode

4. Tcp网关服务 GatewayServer - GatewayServerNode

5. 控制台服务 AdminServer - null

```
private final Server server;
private final ServiceNode node;
```
开启，初始化成功后注册并启动下一个BootJob
```
 public void start() {
        server.init();
        server.start(new Listener() {
            @Override
            public void onSuccess(Object... args) {
                ......
                if (node != null) {//注册应用到zk
                    ServiceRegistryFactory.create().register(node);
                    
                }
                startNext();
            }

            @Override
            public void onFailure(Throwable cause) {
                ......
                System.exit(-1);
            }
        });
    }
```
停止
```
protected void stop() {
        stopNext();
        if (node != null) {
            ServiceRegistryFactory.create().deregister(node);
        }
        ......
        server.stop().join();
    }
```
关于ServiceNode以及CommonServiceNode

属于mpush-api的 com.mpush.api.srd

包括服务名称、节点Id、主机地址、端口、附加属性、是否持久化注册ZK
```
    private String host;

    private int port;

    private Map<String, Object> attrs;

    private transient String name;

    private transient String nodeId;

    private transient boolean persistent;
```
注册到ZK的Key为nodePath
```
default String nodePath() {
        return serviceName() + '/' + nodeId();
    }
```
节点信息获取类 ServerNodes
## MPushServer

包含上述核心服务以及ReusableSessionManager、GatewayUDPConnector

MPushServer实现接口MPushContext，可以获取横向基础服务，包括注册发现、MQ、缓存、监控服务
## ServerEventListener

## AtomicReference

## EventBus

