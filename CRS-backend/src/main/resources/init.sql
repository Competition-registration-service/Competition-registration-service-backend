-- Create table for Competition
CREATE TABLE competition (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             name VARCHAR(255),
                             max_number_of_team_members INT,
                             min_number_of_team_members INT,
                             start_date DATE,
                             end_date DATE,
                             cid VARCHAR(255),
                             parent_id INT,
                             ref_competition_id INT,
                             ref_comp_count_id INT,
                             ref_comp_age_id INT,
                             FOREIGN KEY (parent_id) REFERENCES competition(id),
                             FOREIGN KEY (ref_competition_id) REFERENCES ref_value(id),
                             FOREIGN KEY (ref_comp_count_id) REFERENCES ref_value(id),
                             FOREIGN KEY (ref_comp_age_id) REFERENCES ref_value(id)
);

-- Create table for Content
CREATE TABLE content (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         content TEXT,
                         competition_id INT,
                         ref_page_id INT,
                         ref_format_id INT,
                         ref_language_id INT,
                         FOREIGN KEY (competition_id) REFERENCES competition(id),
                         FOREIGN KEY (ref_page_id) REFERENCES ref_value(id),
                         FOREIGN KEY (ref_format_id) REFERENCES ref_value(id),
                         FOREIGN KEY (ref_language_id) REFERENCES ref_value(id)
);

-- Create table for Contestant
CREATE TABLE contestant (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(255),
                            surename BOOLEAN,
                            thirdname VARCHAR(255),
                            nickname VARCHAR(255),
                            phone VARCHAR(20),
                            email VARCHAR(255),
                            vk VARCHAR(255),
                            telegram VARCHAR(255),
                            create_date DATE,
                            change_date DATE,
                            user_id INT,
                            competition_id INT,
                            team_id INT,
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (competition_id) REFERENCES competition(id),
                            FOREIGN KEY (team_id) REFERENCES team(id)
);

-- Create table for Field
CREATE TABLE field (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       select_domain_cid VARCHAR(255),
                       team_field BOOLEAN,
                       cid VARCHAR(255),
                       short_name VARCHAR(255),
                       long_name TEXT,
                       comment TEXT,
                       example_value VARCHAR(255),
                       max_length INT,
                       `order` INT,
                       optional BOOLEAN,
                       competition_id INT,
                       ref_type_id INT,
                       FOREIGN KEY (competition_id) REFERENCES competition(id),
                       FOREIGN KEY (ref_type_id) REFERENCES ref_value(id)
);

-- Create table for FieldValue
CREATE TABLE field_value (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             value TEXT,
                             contestant_id INT,
                             team_id INT,
                             field_id INT,
                             FOREIGN KEY (contestant_id) REFERENCES contestant(id),
                             FOREIGN KEY (team_id) REFERENCES team(id),
                             FOREIGN KEY (field_id) REFERENCES field(id)
);

-- Create table for File
CREATE TABLE file (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      original_file_name VARCHAR(255),
                      storage_file_id VARCHAR(255),
                      competition_id INT,
                      ref_file_id INT,
                      FOREIGN KEY (competition_id) REFERENCES competition(id),
                      FOREIGN KEY (ref_file_id) REFERENCES ref_value(id)
);

-- Create table for RefValue
CREATE TABLE ref_value (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           domain_cid INT,
                           value_cid VARCHAR(255),
                           short_value VARCHAR(255),
                           long_value TEXT,
                           visible BOOLEAN,
                           comment TEXT
);

-- Create table for Team
CREATE TABLE team (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(255),
                      access_code VARCHAR(255),
                      create_date DATE,
                      change_date DATE,
                      competition_id INT,
                      creator_id INT,
                      FOREIGN KEY (competition_id) REFERENCES competition(id),
                      FOREIGN KEY (creator_id) REFERENCES contestant(id)
);

-- Create table for Users
CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(255),
                       surename BOOLEAN,
                       thirdname VARCHAR(255),
                       birth_date DATE,
                       gender VARCHAR(10),
                       login VARCHAR(255),
                       password VARCHAR(255),
                       phone VARCHAR(20),
                       email VARCHAR(255),
                       vk VARCHAR(255),
                       telegram VARCHAR(255),
                       create_date DATE,
                       change_date DATE,
                       activation_code VARCHAR(255),
                       ref_education_id INT,
                       ref_course_id INT,
                       ref_region_id INT,
                       FOREIGN KEY (ref_education_id) REFERENCES ref_value(id),
                       FOREIGN KEY (ref_course_id) REFERENCES ref_value(id),
                       FOREIGN KEY (ref_region_id) REFERENCES ref_value(id)
);

-- Create table for UserCompPerm
CREATE TABLE user_comp_perm (
                                id INT PRIMARY KEY AUTO_INCREMENT,
                                user_id INT,
                                competition_id INT,
                                ref_role_id INT,
                                FOREIGN KEY (user_id) REFERENCES users(id),
                                FOREIGN KEY (competition_id) REFERENCES competition(id),
                                FOREIGN KEY (ref_role_id) REFERENCES ref_value(id)
);
