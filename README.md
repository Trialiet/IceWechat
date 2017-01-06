#IceWechat

##wechat包
###wechat.custom
包含客服消息的数据结构，json格式。
- BaseCustom客服消息基类
- CustomArticle图服客户消息
- CustomImage图片客服消息
- CustomNews多图文客服消息
- CustomText文本客服消息

###wechat.menu
包含自定义菜单的数据结构，json格式。
一个菜单包括多个（最多三个）GroupButton，一个GroupButton包括多个（最多五个）CommonButton。

###wechat.message
包含用户消息的数据结构，XML格式。
####wechat.message.processor
包含用于自动回复逻辑的工具。
#####Interface CommonRule
public boolean map(BaseMessage msg)
根据用户发送的消息进行匹配
#####class ContainsRule implements CommonRule
“包含”条件判断，包括key与value。如果消息中名称为key的参数内容为value，则通过规则。
######Param
String key
规定需要过滤的消息的参数名
String value
规定需要过滤的消息的参数值
######Method
public boolean map(BaseMessage msg)
根据传入的消息进行匹配，是否有名称为key的参数其值为value，有则返回真
#####interface MessageHandler
public BaseMessage handle(BaseMessage msg)
对msg做出处理，向用户返回一条Message
#####class MessageFilter
消息路由，包含多条规则CommonRule和一个对应的处理器MessageHandler，如果传入的消息符合规则，则调用处理器进行处理。
######Method
public BaseMessage process(BaseMessage msg)
消息处理，如果与所有规则匹配，则调用handler进行处理；如果不匹配，则调用defaultHandler处理或不处理。