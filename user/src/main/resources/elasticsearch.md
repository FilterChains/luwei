``
``

## 配置文件详解1.0版

配置文件位于es根目录的config目录下面，有elasticsearch.yml和logging.yml两个配置，主配置文件是elasticsearch.yml，日志配置文件是logging.yml，elasticsearch调用log4j记录日志，所以日志的配置文件可以按照默认的设置，我来介绍下elasticsearch.yml里面的选项。

cluster.name: elasticsearch
配置的集群名称，默认是elasticsearch，es服务会通过广播方式自动连接在同一网段下的es服务，通过多播方式进行通信，同一网段下可以有多个集群，通过集群名称这个属性来区分不同的集群。

node.name: "Franz Kafka"
当前配置所在机器的节点名，你不设置就默认随机指定一个name列表中名字，该name列表在es的jar包中config文件夹里name.txt文件中，其中有很多作者添加的有趣名字。

node.master: true 指定该节点是否有资格被选举成为node（注意这里只是设置成有资格，
不代表该node一定就是master），默认是true，es是默认集群中的第一台机器为master，如果这台机挂了就会重新选举master。

node.data: true 指定该节点是否存储索引数据，默认为true。

index.number_of_shards: 5 设置默认索引分片个数，默认为5片。

index.number_of_replicas: 1 设置默认索引副本个数，默认为1个副本。如果采用默认设置，而你集群只配置了一台机器，那么集群的健康度为yellow，也就是所有的数据都是可用的，但是某些复制没有被分配（

健康度可用 curl 'localhost:9200/_cat/health?v' 查看，
分为绿色、黄色或红色。绿色代表一切正常，集群功能齐全，黄色意味着所有的数据都是可用的，但是某些复制没有被分配，红色则代表因为某些原因，某些数据不可用）。

path.conf: /path/to/conf 设置配置文件的存储路径，默认是es根目录下的config文件夹。

path.data: /path/to/data 设置索引数据的存储路径，默认是es根目录下的data文件夹，可以设置多个存储路径，用逗号隔开，例：

path.data: /path/to/data1,/path/to/data2

path.work: /path/to/work 设置临时文件的存储路径，默认是es根目录下的work文件夹。

path.logs: /path/to/logs 设置日志文件的存储路径，默认是es根目录下的logs文件夹

path.plugins: /path/to/plugins 设置插件的存放路径，默认是es根目录下的plugins文件夹, 插件在es里面普遍使用，用来增强原系统核心功能。

bootstrap.mlockall: true 设置为true来锁住内存不进行swapping。因为当jvm开始swapping时es的效率
会降低，所以要保证它不swap，可以把ES_MIN_MEM和ES_MAX_MEM两个环境变量设置成同一个值，并且保证机器有足够的内存分配给es。
同时也要允许elasticsearch的进程可以锁住内存，linux下启动es之前可以通过`ulimit -l unlimited`命令设置。

network.bind_host: 192.168.0.1 设置绑定的ip地址，可以是ipv4或ipv6的，默认为0.0.0.0，绑定这台机器的任何一个ip。

network.publish_host: 192.168.0.1 设置其它节点和该节点交互的ip地址，如果不设置它会自动判断，值必须是个真实的ip地址。

network.host: 192.168.0.1 这个参数是用来同时设置bind_host和publish_host上面两个参数。

transport.tcp.port: 9300 设置节点之间交互的tcp端口，默认是9300。

transport.tcp.compress: true 设置是否压缩tcp传输时的数据，默认为false，不压缩。

http.port: 9200 设置对外服务的http端口，默认为9200。

http.max_content_length: 100mb 设置内容的最大容量，默认100mb

http.enabled: false 是否使用http协议对外提供服务，默认为true，开启。

gateway.type: local gateway的类型，默认为local即为本地文件系统，可以设置为本地文件系统，分布式文件系统，hadoop的HDFS，和amazon的s3服务器等。

gateway.recover_after_nodes: 1 设置集群中N个节点启动时进行数据恢复，默认为1。

gateway.recover_after_time: 5m 设置初始化数据恢复进程的超时时间，默认是5分钟。

gateway.expected_nodes: 2 设置这个集群中节点的数量，默认为2，一旦这N个节点启动，就会立即进行数据恢复。

cluster.routing.allocation.node_initial_primaries_recoveries: 4 初始化数据恢复时，并发恢复线程的个数，默认为4。

cluster.routing.allocation.node_concurrent_recoveries: 2 添加删除节点或负载均衡时并发恢复线程的个数，默认为4。

indices.recovery.max_size_per_sec: 0 设置数据恢复时限制的带宽，如入100mb，默认为0，即无限制。

indices.recovery.concurrent_streams: 5 设置这个参数来限制从其它分片恢复数据时最大同时打开并发流的个数，默认为5。

discovery.zen.minimum_master_nodes: 1 设置这个参数来保证集群中的节点可以知道其它N个有master资格的节点。默认为1，对于大的集群来说，可以设置大一点的值（2-4）

discovery.zen.ping.timeout: 3s 设置集群中自动发现其它节点时ping连接超时时间，默认为3秒，对于比较差的网络环境可以高点的值来防止自动发现时出错。

discovery.zen.ping.multicast.enabled: false 设置是否打开多播发现节点，默认是true。

discovery.zen.ping.unicast.hosts: ["host1", "host2:port", "host3[portX-portY]"]
设置集群中master节点的初始列表，可以通过这些节点来自动发现新加入集群的节点。

＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝

## 配置文件2.0版

配置文件位于%ES_HOME%/config/elasticsearch.yml文件中，用Editplus打开它，你便可以进行配置。 所有的配置都可以使用环境变量，例如： node.rack: ${RACK_ENV_VAR}
表示环境变量中有一个RACK_ENV_VAR变量。 下面列举一下elasticsearch的可配置项： 1. 集群名称，默认为elasticsearch： cluster.name: elasticsearch 2.
节点名称，es启动时会自动创建节点名称，但你也可进行配置： node.name: "Franz Kafka"

3. 是否作为主节点，每个节点都可以被配置成为主节点，默认值为true： node.master: true 4. 是否存储数据，即存储索引片段，默认值为true： node.data: true
   master和data同时配置会产生一些奇异的效果： 1) 当master为false，而data为true时，会对该节点产生严重负荷； 2) 当master为true，而data为false时，该节点作为一个协调者； 3)
   当master为false，data也为false时，该节点就变成了一个负载均衡器。
   你可以通过连接http://localhost:9200/_cluster/health或者http://localhost:9200/_cluster/nodes，或者使用插件http://github.com/lukas-vlcek/bigdesk或http://mobz.github.com/elasticsearch-head来查看集群状态。
5. 每个节点都可以定义一些与之关联的通用属性，用于后期集群进行碎片分配时的过滤： node.rack: rack314 6. 默认情况下，多个节点可以在同一个安装路径启动，如果你想让你的es只启动一个节点，可以进行如下设置：
   node.max_local_storage_nodes: 1 7. 设置一个索引的碎片数量，默认值为5： index.number_of_shards: 5 8. 设置一个索引可被复制的数量，默认值为1：
   index.number_of_replicas: 1 当你想要禁用公布式时，你可以进行如下设置： index.number_of_shards: 1 index.number_of_replicas: 0
   这两个属性的设置直接影响集群中索引和搜索操作的执行。假设你有足够的机器来持有碎片和复制品，那么可以按如下规则设置这两个值： 1) 拥有更多的碎片可以提升索引执行能力，并允许通过机器分发一个大型的索引； 2)
   拥有更多的复制器能够提升搜索执行能力以及集群能力。 对于一个索引来说，number_of_shards只能设置一次，而number_of_replicas可以使用索引更新设置API在任何时候被增加或者减少。
   ElasticSearch关注加载均衡、迁移、从节点聚集结果等等。可以尝试多种设计来完成这些功能。 可以连接http://localhost:9200/A/_status来检测索引的状态。
9. 配置文件所在的位置，即elasticsearch.yml和logging.yml所在的位置： path.conf: /path/to/conf 10. 分配给当前节点的索引数据所在的位置： path.data:
   /path/to/data 可以可选择的包含一个以上的位置，使得数据在文件级别跨越位置，这样在创建时就有更多的自由路径，如： path.data: /path/to/data1,/path/to/data2 11. 临时文件位置：
   path.work: /path/to/work 12. 日志文件所在位置： path.logs: /path/to/logs 13. 插件安装位置： path.plugins: /path/to/plugins 14.
   插件托管位置，若列表中的某一个插件未安装，则节点无法启动： plugin.mandatory: mapper-attachments,lang-groovy 15.
   JVM开始交换时，ElasticSearch表现并不好：你需要保障JVM不进行交换，可以将bootstrap.mlockall设置为true禁止交换： bootstrap.mlockall: true
   请确保ES_MIN_MEM和ES_MAX_MEM的值是一样的，并且能够为ElasticSearch分配足够的内在，并为系统操作保留足够的内存。 16.
   默认情况下，ElasticSearch使用0.0.0.0地址，并为http传输开启9200-9300端口，为节点到节点的通信开启9300-9400端口，也可以自行设置IP地址： network.bind_host:
   192.168.0.1
17. publish_host设置其他节点连接此节点的地址，如果不设置的话，则自动获取，publish_host的地址必须为真实地址： network.publish_host: 192.168.0.1 18.
    bind_host和publish_host可以一起设置： network.host: 192.168.0.1 19. 可以定制该节点与其他节点交互的端口： transport.tcp.port: 9300 20.
    节点间交互时，可以设置是否压缩，转为为不压缩： transport.tcp.compress: true 21. 可以为Http传输监听定制端口： http.port: 9200 22. 设置内容的最大长度：
    http.max_content_length: 100mb 23. 禁止HTTP http.enabled: false 24.
    网关允许在所有集群重启后持有集群状态，集群状态的变更都会被保存下来，当第一次启用集群时，可以从网关中读取到状态，默认网关类型（也是推荐的）是local： gateway.type: local 25. 允许在N个节点启动后恢复过程：
    gateway.recover_after_nodes: 1 26. 设置初始化恢复过程的超时时间： gateway.recover_after_time: 5m 27. 设置该集群中可存在的节点上限：
    gateway.expected_nodes: 2 28. 设置一个节点的并发数量，有两种情况，一种是在初始复苏过程中：
    cluster.routing.allocation.node_initial_primaries_recoveries: 4 另一种是在添加、删除节点及调整时：
    cluster.routing.allocation.node_concurrent_recoveries: 2 29. 设置复苏时的吞吐量，默认情况下是无限的： indices.recovery.max_size_per_sec:
    0
30. 设置从对等节点恢复片段时打开的流的数量上限： indices.recovery.concurrent_streams: 5 31. 设置一个集群中主节点的数量，当多于三个节点时，该值可在2-4之间：
    discovery.zen.minimum_master_nodes: 1 32. 设置ping其他节点时的超时时间，网络比较慢时可将该值设大： discovery.zen.ping.timeout: 3s
    http://elasticsearch.org/guide/reference/modules/discovery/zen.html上有更多关于discovery的设置。
33. 禁止当前节点发现多个集群节点，默认值为true： discovery.zen.ping.multicast.enabled: false 34. 设置新节点被启动时能够发现的主节点列表（主要用于不同网段机器连接）：

discovery.zen.ping.unicast.hosts: ["host1", "host2:port", "host3[portX-portY]"]

       35.设置是否可以通过正则或者_all删除或者关闭索引

action.destructive_requires_name 默认false 允许 可设置true不允许