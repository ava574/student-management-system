-- USERS
INSERT INTO users (id, email, password, role) VALUES
(1, 'admin@college.com', 'admin123', 'ADMIN'),
(2, 'ravi@student.com', '12345', 'STUDENT'),
(3, 'neha@student.com', '12345', 'STUDENT');

-- STUDENTS
INSERT INTO students (id, name, email, course, cgpa, skills) VALUES
(1, 'Ravi Patil', 'ravi@student.com', 'BE Computer', 8.2, 'Java, Spring, SQL'),
(2, 'Neha Kulkarni', 'neha@student.com', 'BE IT', 8.8, 'Python, Data Science'),
(3, 'Ajay Deshmukh', 'ajay@example.com', 'BE ENTC', 7.5, 'Embedded, C'),
(4, 'Pooja Jadhav', 'pooja@example.com', 'MBA', 8.1, 'Marketing, Communication');

-- COMPANIES
INSERT INTO companies (id, name, role, min_cgpa, location) VALUES
(1, 'TCS', 'Software Engineer', 7.0, 'Pune'),
(2, 'Infosys', 'System Engineer', 7.5, 'Bengaluru'),
(3, 'HDFC Bank', 'Management Trainee', 7.8, 'Mumbai'),
(4, 'Cognizant', 'Programmer Analyst', 7.0, 'Chennai');

-- PLACEMENTS
INSERT INTO placements (id, student_id, company_id, status) VALUES
(1, 1, 1, 'Placed'),
(2, 2, 2, 'Shortlisted');