
decode(字段, 条件1, 表达式1, 条件2, 表达式2, ...表达式n)
FROM 表名1,表名2 WHERE 表名1.字段名1=表名2.字段名2...
where dept.deptno = emp.deptno(+) 
select users.ename || '的上级是' ||boss.ename
from emp users,emp boss
where users.mgr = boss.empno(+);
flashback table table_name to before drop
purge recyclebin

gender varchar2(2) not null check(gender in ('男','女')),
salary number(6) not null check(salary between 6000 and 8000)






































