/*
SQLyog v10.2
MySQL - 5.7.18-txsql-log
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `mapper.card` (
`id` int (11),
`mpid` varchar (192),
`type` int (11),
`name` varchar (192),
`bg` varchar (192),
`wish` blob ,
`write_name` varchar (96),
`send_num` int (11),
`create_time` datetime ,
`condition` tinyint (4),
`send_time` int (11)
);





/*
SQLyog v10.2
MySQL - 5.7.18-txsql-log
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `task_info` (
`id` int (11),
`mpid` varchar (192),
`task_id` int (11),
`phone` varchar (48),
`cus_name` varchar (765),
`send_time` datetime ,
`view_time` datetime ,
`is_del` tinyint (4),
`task_time` datetime ,
`birthday` date ,
`cid` varchar (765)
);
