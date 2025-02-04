--liquibase formatted sql

--changeset nikita.mykhailov:2

INSERT INTO Person (id, first_name, last_name, email)
VALUES
    ('488352d9-ae2f-4599-85a0-ae938dc3db77', 'John', 'Doe', 'john.doe@example.com'),
    ('a4735e01-6026-411b-94dc-793aae18a316', 'Jane', 'Smith', 'jane.smith@example.com'),
    ('ce85f16f-fc17-4a85-addd-03a2b7712651', 'Alice', 'Brown', 'alice.brown@example.com'),
    ('75280e71-8231-4cd6-8988-de0bdd08d8c7', 'Bob', 'Johnson', 'bob.johnson@example.com');

INSERT INTO Address (
    id, street, city, postal_code, country, person_id
) VALUES
      ('1bbaf2ca-7567-4953-86bb-23e08b747e9a', 'Street1', 'City1', '12345', 'Country1', '488352d9-ae2f-4599-85a0-ae938dc3db77'), -- John Doe
      ('4d009846-e687-404a-8654-d28ecd9a6ddb', 'Street2', 'City2', '54321', 'Country2', 'a4735e01-6026-411b-94dc-793aae18a316'), -- Jane Smith
      ('605818e6-fe6a-42e2-96d5-34496022f5f1', 'Street3', 'City3', '54321', 'Country3', '75280e71-8231-4cd6-8988-de0bdd08d8c7'), -- Alice Brown
      ('2cd5408f-4c90-4d1c-be9c-5e84a817976a', 'Street4', 'City4', '54321', 'Country4', '75280e71-8231-4cd6-8988-de0bdd08d8c7'), -- Alice Brown
      ('ddb0b0b0-858f-4ea9-8249-cb7b163b1e37', 'Street5', 'City5', '54321', 'Country5', 'a4735e01-6026-411b-94dc-793aae18a316'); -- Jane Smith

INSERT INTO Teacher (id, degree, employment_type, person_id)
VALUES
    ('878d87ca-eb4d-47d4-9b7d-b2ac59002d85', 'PhD', 'Full-time', '488352d9-ae2f-4599-85a0-ae938dc3db77'),
    ('ad69d8c6-aa2c-4b5e-863a-ff6ad7395daf', 'Masters', 'Part-time', 'a4735e01-6026-411b-94dc-793aae18a316');

INSERT INTO Student (id, expected_graduation, person_id)
VALUES
    ('b4464831-c4ec-4025-a64f-aa2db3ffc3f9', '2025-06-01', 'ce85f16f-fc17-4a85-addd-03a2b7712651'),
    ('ac5652d6-76b9-4864-8d4a-26cf96403801', '2024-12-15', '75280e71-8231-4cd6-8988-de0bdd08d8c7');

INSERT INTO Classroom (id, name, teacher_id)
VALUES
    ('9f73160a-a327-4417-bfb3-89c6df428849', 'Math 101', '878d87ca-eb4d-47d4-9b7d-b2ac59002d85'),
    ('f19eaa55-43f9-4153-b54e-4a2c80a7341d', 'History 201', 'ad69d8c6-aa2c-4b5e-863a-ff6ad7395daf');


INSERT INTO Enrollment (id, classroom_id, student_id)
VALUES
    ('502d64b1-5dba-42dd-a138-a233ecf402a3', '9f73160a-a327-4417-bfb3-89c6df428849', 'b4464831-c4ec-4025-a64f-aa2db3ffc3f9'),
    ('8018a98c-d0d2-4aec-a8fe-09bd17750815', 'f19eaa55-43f9-4153-b54e-4a2c80a7341d', 'ac5652d6-76b9-4864-8d4a-26cf96403801'),
    ('9c54e02f-b689-4129-a240-289abf7c65bc', '9f73160a-a327-4417-bfb3-89c6df428849', 'ac5652d6-76b9-4864-8d4a-26cf96403801'); -- Bob enrolled in Math 101 as well