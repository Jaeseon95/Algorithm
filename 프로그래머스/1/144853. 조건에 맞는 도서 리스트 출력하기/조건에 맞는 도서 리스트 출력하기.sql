-- 코드를 입력하세요
SELECT BOOK_ID, DATE_FORMAT(published_date, '%Y-%m-%d') as 'PUBLISHED_DATE'
FROM BOOK
WHERE CATEGORY='인문' AND DATE_FORMAT(published_date, '%Y')='2021'
ORDER BY published_date;