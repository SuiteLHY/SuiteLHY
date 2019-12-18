/**
 * Service 层：逻辑业务层。
 *
 * 主要处理在数据持久层之上的专一功能业务逻辑
 * 另外注意的是，Web 交互的业务逻辑应该在 web 层实现并管理，
 * -> 而不是统统塞到 Service 层；否则会导致 Service 层内容
 * -> 杂驳，随着项目体量增长而难以维护。
 */
package spring.example.SpringJDBC.service;