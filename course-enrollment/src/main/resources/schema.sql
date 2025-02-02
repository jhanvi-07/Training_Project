CREATE TABLE IF NOT EXISTS `course` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(100) NOT NULL,
  `duration` INT NOT NULL,
  `author` VARCHAR(30) NOT NULL,
  `created_at` DATE DEFAULT NULL,
  `created_by` VARCHAR(30) DEFAULT NULL,
  `updated_at` DATE DEFAULT NULL,
  `updated_by` VARCHAR(30) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `enrollment` (
  `e_id` INT AUTO_INCREMENT PRIMARY KEY,
  `emp_id` INT NOT NULL,
  `course_id` INT NOT NULL,
  `status` BOOLEAN NOT NULL
);