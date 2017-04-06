# $Id: create_db_baylandtag.sql 1570 2015-12-11 14:15:54Z michael $
#
# create db baylandtag, user test - kein PW  (MySQL 5.5.28)

CONNECT mysql;

DROP DATABASE IF EXISTS baylandtag;
CREATE DATABASE baylandtag CHARACTER SET utf8;

DELETE FROM user WHERE user='test';
INSERT INTO user SET Host='localhost',
                     User='test',
                     Password='';

DELETE FROM db WHERE Db='baylandtag';
INSERT INTO db SET Host='localhost',
                   Db='baylandtag',
                   User='test',
                   Select_priv='Y',
                   Insert_priv='Y',
                   Update_priv='Y',
                   Delete_priv='Y',
                   Create_priv='Y',
                   Drop_priv='Y',
                   Grant_priv='Y',
                   References_priv='Y',
                   Index_priv='Y',
                   Alter_priv='Y',
                   Create_tmp_table_priv='Y',
                   Lock_tables_priv='Y',
                   Create_view_priv='Y',
                   Show_view_priv='Y',
                   Create_routine_priv='Y',
                   Alter_routine_priv='Y',
                   Execute_priv='Y',
                   Event_priv='Y',
                   Trigger_priv='Y';
 
FLUSH PRIVILEGES;

   
