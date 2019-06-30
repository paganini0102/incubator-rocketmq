/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.rocketmq.client.producer;

import org.apache.rocketmq.client.MQAdmin;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

public interface MQProducer extends MQAdmin {
    void start() throws MQClientException;

    void shutdown();

    List<MessageQueue> fetchPublishMessageQueues(final String topic) throws MQClientException;

    /**
     * 同步发送消息，具体发送到主题中的哪个消息队列由负载算法决定
     *
     * @param msg
     * @return
     * @throws MQClientException
     * @throws RemotingException
     * @throws MQBrokerException
     * @throws InterruptedException
     */
    SendResult send(final Message msg) throws MQClientException, RemotingException, MQBrokerException,
            InterruptedException;

    /**
     * 同步发送消息，如果发送超过timeout则抛出超时异常
     *
     * @param msg
     * @param timeout
     * @return
     * @throws MQClientException
     * @throws RemotingException
     * @throws MQBrokerException
     * @throws InterruptedException
     */
    SendResult send(final Message msg, final long timeout) throws MQClientException,
            RemotingException, MQBrokerException, InterruptedException;

    /**
     * 异步发送消息，sendCallback参数是消息发送成功后的回调方法
     *
     * @param msg
     * @param sendCallback
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     */
    void send(final Message msg, final SendCallback sendCallback) throws MQClientException,
            RemotingException, InterruptedException;

    /**
     * 异步发送消息，如果发送超过timeout指定的值，则抛出超时异常
     *
     * @param msg
     * @param sendCallback
     * @param timeout
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     */
    void send(final Message msg, final SendCallback sendCallback, final long timeout)
            throws MQClientException, RemotingException, InterruptedException;

    /**
     * 单向消息发送，就是不在乎发送结果，消息发送出去后该方法立即返回
     *
     * @param msg
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     */
    void sendOneway(final Message msg) throws MQClientException, RemotingException,
            InterruptedException;

    /**
     * 同步方式发送消息，发送到指定消息队列
     *
     * @param msg
     * @param mq
     * @return
     * @throws MQClientException
     * @throws RemotingException
     * @throws MQBrokerException
     * @throws InterruptedException
     */
    SendResult send(final Message msg, final MessageQueue mq) throws MQClientException,
            RemotingException, MQBrokerException, InterruptedException;

    /**
     * 异步方式发送消息，发送到指定消息队列
     *
     * @param msg
     * @param mq
     * @param timeout
     * @return
     * @throws MQClientException
     * @throws RemotingException
     * @throws MQBrokerException
     * @throws InterruptedException
     */
    SendResult send(final Message msg, final MessageQueue mq, final long timeout)
            throws MQClientException, RemotingException, MQBrokerException, InterruptedException;

    /**
     * 异步方式发送消息，发送到指定消息队列
     *
     * @param msg
     * @param mq
     * @param sendCallback
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     */
    void send(final Message msg, final MessageQueue mq, final SendCallback sendCallback)
            throws MQClientException, RemotingException, InterruptedException;

    /**
     * @param msg
     * @param mq
     * @param sendCallback
     * @param timeout
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     */
    void send(final Message msg, final MessageQueue mq, final SendCallback sendCallback, long timeout)
            throws MQClientException, RemotingException, InterruptedException;

    /**
     * 单向方式发送消息，发送到指定的消息队列
     *
     * @param msg
     * @param mq
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     */
    void sendOneway(final Message msg, final MessageQueue mq) throws MQClientException,
            RemotingException, InterruptedException;

    /**
     * 消息发送，指定消息选择算法，覆盖消息生产者默认的消息队列负载
     *
     * @param msg
     * @param selector
     * @param arg
     * @return
     * @throws MQClientException
     * @throws RemotingException
     * @throws MQBrokerException
     * @throws InterruptedException
     */
    SendResult send(final Message msg, final MessageQueueSelector selector, final Object arg)
            throws MQClientException, RemotingException, MQBrokerException, InterruptedException;

    /**
     * @param msg
     * @param selector
     * @param arg
     * @param timeout
     * @return
     * @throws MQClientException
     * @throws RemotingException
     * @throws MQBrokerException
     * @throws InterruptedException
     */
    SendResult send(final Message msg, final MessageQueueSelector selector, final Object arg,
                    final long timeout) throws MQClientException, RemotingException, MQBrokerException,
            InterruptedException;

    void send(final Message msg, final MessageQueueSelector selector, final Object arg,
        final SendCallback sendCallback) throws MQClientException, RemotingException,
        InterruptedException;

    void send(final Message msg, final MessageQueueSelector selector, final Object arg,
        final SendCallback sendCallback, final long timeout) throws MQClientException, RemotingException,
        InterruptedException;

    void sendOneway(final Message msg, final MessageQueueSelector selector, final Object arg)
        throws MQClientException, RemotingException, InterruptedException;

    TransactionSendResult sendMessageInTransaction(final Message msg,
        final LocalTransactionExecuter tranExecuter, final Object arg) throws MQClientException;
}
