# 1、 查询Student表中的所有记录的Sname、Ssex和Class列。
SELECT
  SNAME,
  SSEX,
  CLASS
FROM student;

# 2、 查询教师所有的单位即不重复的Depart列。
SELECT DISTINCT DEPART
FROM teacher;

# 3、 查询Student表的所有记录。
SELECT *
FROM student;

# 4、 查询Score表中成绩在60到80之间的所有记录。
SELECT *
FROM score
WHERE DEGREE BETWEEN 60 AND 80;

# 5、 查询Score表中成绩为85，86或88的记录。
SELECT *
FROM score
WHERE DEGREE IN (85, 86, 88);

# 6、 查询Student表中“95031”班或性别为“女”的同学记录。
SELECT *
FROM student
WHERE CLASS = 95031 OR SSEX = '女';

# 7、 以Class降序查询Student表的所有记录。
SELECT *
FROM student
ORDER BY CLASS DESC;

# 8、 以Cno升序、Degree降序查询Score表的所有记录。
SELECT *
FROM score
ORDER BY CNO ASC, DEGREE DESC;

# 9、 查询“95031”班的学生人数。
SELECT count(1)
FROM student
WHERE CLASS = 95031;

# 10 查询DEGREE最大值
SELECT MAX(DEGREE)
FROM score;

# 10.1、查询Score表中的最高分的学生学号和课程号。
SELECT
  SNO,
  CNO
FROM score
WHERE DEGREE = (SELECT MAX(DEGREE)
                FROM score);

# 11、查询‘3-105’号课程的平均分。
SELECT AVG(DEGREE)
FROM score
WHERE CNO = '3-105';

# 12 查询至少5名学生选修的课程
SELECT
  CNO,
  SNO
FROM score
GROUP BY CNO
HAVING count(SNO) >= 5;

# 12、查询Score表中至少有5名学生选修的并以3开头的课程的平均分数。
SELECT
  CNO,
  AVG(DEGREE)
FROM score
WHERE CNO LIKE '3%'
GROUP BY CNO
HAVING count(CNO) >= 5;

# 13、查询最低分大于70，最高分小于90的Sno列。
# SELECT SNO, DEGREE FROM score WHERE DEGREE BETWEEN 70 AND 90 ORDER BY DEGREE ASC;
SELECT
  SNO,
  DEGREE
FROM score
GROUP BY SNO
HAVING MIN(DEGREE) > 70 AND MAX(DEGREE) < 90;

# 14、查询所有学生的Sname、Cno和Degree列。
SELECT
  A.SNAME,
  A.SNO,
  B.CNO,
  B.DEGREE
FROM student AS A
  JOIN score AS B ON A.SNO = B.SNO
ORDER BY A.SNO;
SELECT
  student.SNAME,
  student.SNO,
  score.CNO,
  score.DEGREE
FROM student
  INNER JOIN score ON student.SNO = score.SNO
ORDER BY student.SNO;
SELECT
  student.SNAME,
  score.CNO,
  score.DEGREE
FROM student
  JOIN score ON student.SNO = score.SNO
ORDER BY student.SNO;

# 15、查询所有学生的Sno、Cname和Degree列。
SELECT
  score.SNO,
  score.DEGREE,
  course.CNAME
FROM score
  INNER JOIN course ON score.CNO = course.CNO;
SELECT
  score.SNO,
  score.DEGREE,
  course.CNAME
FROM score AS score
  JOIN course AS course ON score.CNO = course.CNO;

# 16、查询所有学生的Sname、Cname和Degree列。
SELECT
  student.SNAME,
  course.CNAME,
  score.DEGREE
FROM student AS student
  JOIN score AS score ON student.SNO = score.SNO
  JOIN course AS course ON score.CNO = course.CNO
ORDER BY student.SNAME DESC;
SELECT
  student.SNAME,
  course.CNAME,
  score.DEGREE
FROM student
  INNER JOIN (course, score) ON (student.SNO = score.SNO AND score.CNO = course.CNO)
ORDER BY student.SNAME DESC;

# 17、查询“95033”班所选课程的平均分。;
SELECT AVG(DEGREE)
FROM student
  JOIN score ON student.SNO = score.SNO
WHERE CLASS = 95033;

# 18、假设使用如下命令建立了一个grade表：
# create table grade(low INT(3),upp INT(3),rank char(1));
# insert into grade values(90,100,'A');
# insert into grade values(80,89,'B');
# insert into grade values(70,79,'C');
# insert into grade values(60,69,'D');
# insert into grade values(0,59,'E');
# commit;
# 现查询所有同学的Sno、Cno和rank列。
SELECT
  score.SNO,
  score.CNO,
  grade.rank
FROM score
  JOIN grade
WHERE score.DEGREE BETWEEN grade.low AND grade.upp;
SELECT
  score.SNO,
  score.CNO,
  grade.rank
FROM score AS score, grade AS grade
WHERE score.DEGREE BETWEEN grade.low AND grade.upp;
SELECT
  score.SNO,
  score.CNO,
  grade.rank
FROM score score, grade grade
WHERE score.DEGREE BETWEEN grade.low AND grade.upp;

# 19、查询选修“3-105”课程的成绩高于“109”号同学成绩的所有同学的记录。
SELECT *
FROM score
WHERE CNO = '3-105' AND DEGREE > (SELECT DEGREE
                                  FROM score
                                  WHERE CNO = '3-105' AND SNO = 109);
SELECT A.*
FROM SCORE A
  JOIN SCORE B
WHERE A.CNO = '3-105' AND A.DEGREE > B.DEGREE AND B.SNO = '109' AND B.CNO = '3-105';

# 20、查询score中选学一门以上课程的同学中分数为非最高分成绩的记录。
SELECT *
FROM score
WHERE DEGREE < (SELECT MAX(DEGREE)
                FROM score)
GROUP BY SNO
HAVING COUNT(SNO) > 1;

# 21、查询成绩高于学号为“109”、课程号为“3-105”的成绩的所有记录。
SELECT *
FROM score
WHERE CNO = '3-105' AND DEGREE > (SELECT DEGREE
                                  FROM score
                                  WHERE SNO = 109 AND CNO = '3-105'); # 好理解
SELECT A.*
FROM score A
  JOIN score B ON A.CNO = '3-105' AND B.CNO = '3-105' AND B.SNO = 109 AND A.DEGREE > B.DEGREE;

# 22、查询和学号为108的同学同年出生的所有学生的Sno、Sname和Sbirthday列。
SELECT
  SNO,
  SNAME,
  SBIRTHDAY
FROM student
WHERE SBIRTHDAY = (SELECT SBIRTHDAY
                   FROM student
                   WHERE SNO = 108);
SELECT
  A.SNO,
  A.SNAME,
  A.SBIRTHDAY
FROM student AS A
  JOIN student AS B ON B.SNO = 108 AND A.SBIRTHDAY = B.SBIRTHDAY;
SELECT
  A.SNO,
  A.SNAME,
  A.SBIRTHDAY
FROM student A
  JOIN student B
WHERE B.SNO = 108 AND A.SBIRTHDAY = B.SBIRTHDAY;

# 23、查询“张旭“教师任课的学生成绩。
SELECT DEGREE
FROM score
WHERE CNO =
      (SELECT CNO
       FROM course
       WHERE TNO =
             (SELECT TNO
              FROM teacher
              WHERE TNAME = '张旭'));
SELECT score.DEGREE
FROM score
  JOIN (course, teacher) ON course.TNO = teacher.TNO AND course.CNO = score.CNO AND teacher.TNAME = '张旭';

# 24、查询选修某课程的同学人数多于5人的教师姓名。
SELECT TNAME
FROM teacher
WHERE TNO =
      (SELECT TNO
       FROM course
       WHERE CNO =
             (SELECT CNO
              FROM score
              GROUP BY CNO
              HAVING COUNT(CNO) > 5));
SELECT teacher.TNAME
FROM teacher
  JOIN (course, score) ON teacher.TNO = course.TNO AND course.CNO = score.CNO
GROUP BY score.CNO
HAVING COUNT(score.CNO) > 5;

# 25、查询95033班和95031班全体学生的记录。
SELECT *
FROM student
WHERE CLASS = '95033' OR CLASS = '95031';

# 26、查询存在有85分以上成绩的课程Cno.
SELECT DISTINCT CNO
FROM score
WHERE DEGREE > 85
ORDER BY CNO DESC;
SELECT CNO
FROM SCORE
GROUP BY CNO
HAVING MAX(DEGREE) > 85
ORDER BY CNO DESC;

# 27、查询出“计算机系“教师所教课程的成绩表。
SELECT DEGREE
FROM score
  JOIN (teacher, course) ON teacher.DEPART = '计算机系' AND teacher.TNO = course.TNO AND course.CNO = score.CNO;

# 28、查询“计算机系”与“电子工程系“不同职称的教师的Tname和Prof。
SELECT
  TNAME,
  PROF
FROM teacher
WHERE (DEPART = '计算机系' OR DEPART = '电子工程系') AND PROF NOT IN (SELECT PROF
                                                             FROM teacher
                                                             WHERE DEPART = '电子工程系');

# 29、查询选修编号为“3-105“课程且成绩至少高于选修编号为“3-245”的同学的Cno、Sno和Degree,并按Degree从高到低次序排序。
SELECT
  CNO,
  SNO,
  DEGREE
FROM score
WHERE CNO = '3-105' AND DEGREE > ANY (SELECT MAX(DEGREE)
                                      FROM score
                                      WHERE CNO = '3-245')
ORDER BY DEGREE DESC;

# 30、查询选修编号为“3-105”且成绩高于选修编号为“3-245”课程的同学的Cno、Sno和Degree.
SELECT
  CNO,
  SNO,
  DEGREE
FROM score
WHERE CNO = '3-105' AND DEGREE > ALL (SELECT MAX(DEGREE)
                                      FROM score
                                      WHERE CNO = '3-245')
ORDER BY DEGREE DESC;

# 31、查询所有教师和同学的name、sex和birthday.
SELECT
  SNAME     AS NAME,
  SSEX      AS SEX,
  SBIRTHDAY AS BIRTHDAY
FROM student
UNION SELECT
        TSEX      AS SEX,
        TNAME     AS NAME,
        TBIRTHDAY AS BIRTHDAY
      FROM teacher;

# 32、查询所有“女”教师和“女”同学的name、sex和birthday.
SELECT
  SNAME     AS NAME,
  SSEX      AS SEX,
  SBIRTHDAY AS BIRTHDAY
FROM student
WHERE SSEX = '女'
UNION SELECT
        TNAME     AS NAME,
        TSEX      AS SEX,
        TBIRTHDAY AS BIRTHDAY
      FROM teacher
      WHERE TSEX = '女';

# 33、查询成绩比该课程平均成绩低的同学的成绩表。
SELECT A.*
FROM score A
WHERE DEGREE < (SELECT AVG(DEGREE)
                FROM score B
                WHERE A.CNO = B.CNO);
SELECT A.*
FROM SCORE A
WHERE DEGREE < (SELECT AVG(DEGREE)
                FROM SCORE B
                WHERE A.CNO = B.CNO);

# 34、查询所有任课教师的Tname和Depart.
# exists 只是检测row的存在与否，返回一个布尔值
# 两种方法返回的结果一样，但是结果的顺序不一样
SELECT
  TNAME,
  DEPART
FROM teacher A
WHERE EXISTS(SELECT *
             FROM course B
             WHERE A.TNO = B.TNO)
ORDER BY A.TNO DESC;
SELECT
  A.TNAME,
  A.DEPART
FROM teacher A
  JOIN course B
WHERE A.TNO = B.TNO
ORDER BY A.TNO DESC;

# 35、查询所有未任课教师的Tname和Depart.
SELECT
  TNAME,
  DEPART
FROM teacher A
WHERE NOT EXISTS(SELECT *
                 FROM course B
                 WHERE A.TNO = B.TNO);

# 36、查询至少有2名男生的班号。
SELECT CLASS
FROM student
WHERE SSEX = '男'
GROUP BY CLASS
HAVING COUNT(SSEX) >= 2;

# 37、查询Student表中不姓“王”的同学记录。
# % 代替所有 %% 代替所有但不代替空格
SELECT *
FROM student
WHERE SNAME NOT LIKE '王%';

# 38、查询Student表中每个学生的姓名和年龄。
SELECT
  SNAME,
  YEAR(NOW()) - YEAR(SBIRTHDAY) AS AGE
FROM student;

# 39、查询Student表中最大和最小的Sbirthday日期值。
SELECT
  MAX(SBIRTHDAY) AS MAXB,
  MIN(SBIRTHDAY) AS MINB
FROM student;

# 40、以班号和年龄从大到小的顺序查询Student表中的全部记录。
SELECT *
FROM student
ORDER BY CLASS DESC, (SELECT YEAR(NOW()) - YEAR(SBIRTHDAY) AS AGE) DESC;
SELECT
  SNAME,
  SSEX,
  SNO,
  CLASS,
  SBIRTHDAY,
  YEAR(NOW()) - YEAR(SBIRTHDAY) AS AGE
FROM student
ORDER BY CLASS DESC, AGE DESC;

# 41、查询“男”教师及其所上的课程。
SELECT *
FROM teacher
WHERE TSEX = '男';
SELECT
  A.*,
  B.CNAME
FROM teacher A, course B
WHERE A.TSEX = '男' AND A.TNO = B.TNO;

# 42、查询最高分同学的Sno、Cno和Degree列。
SELECT
  SNO,
  CNO,
  MAX(DEGREE)
FROM score;

# 43、查询和“李军”同性别的所有同学的Sname.
SELECT SNAME
FROM student
WHERE SSEX = (SELECT SSEX
              FROM student
              WHERE SNAME = '李军');

# 44、查询和“李军”同性别并同班的同学Sname.
SELECT A.SNAME
FROM student A, student B
WHERE A.CLASS = B.CLASS AND A.SSEX = B.SSEX AND B.SNAME = '李军';

# 45、查询所有选修“计算机导论”课程的“男”同学的成绩表
SELECT
  score.*,
  student.SSEX,
  course.CNAME
FROM score, course, student
WHERE score.CNO = course.CNO AND score.SNO = student.SNO AND course.CNAME = '计算机导论' AND student.SSEX = '男'