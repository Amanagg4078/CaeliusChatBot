CREATE DATABASE chatbot;
USE chatbot;

CREATE TABLE QnA(
	Qno INT auto_increment primary key,
    Query varchar(3000),
    Result varchar(3000)
    
);

INSERT INTO QnA (Query, Result)
VALUES
('Hi', 'Hi, How are you?'),
('Who are you?', 'I am a chatbot made using Java. I am here to provide answers to your queries. Please feel free to ask me any questions.');

INSERT INTO QnA (Query, Result)
VALUES
('What is Caelius Consulting?', 'Caelius Consulting is a top-rated business consulting and systems integration firm established in Dallas, Texas, in 2018.'),
('Where are the offices of Caelius Consulting located?', 'Caelius Consulting has offices in Canada, India, Singapore, and the United States.'),
('What services does Caelius Consulting provide?', 'Caelius Consulting specializes in providing full-stack services across platforms.'),
('Who are some key members of the leadership team at Caelius Consulting?', 'The leadership team at Caelius Consulting includes Mr. Munish Dadwal (Chief Technology Officer), Mr. Mankesh Dadhwal (Chief Outsourcing Officer), Ms. Shilpa Dadhwal (Founder & Director), Ms. Lavina Ambani (VP Customer Success), Mr. Jayadeep Apte (Partner, Singapore), and Dr. Mukta Dewan (Chief People Officer).'),
('What are some key technologies used by Caelius Consulting?', 'Key technologies used at Caelius Consulting include MuleSoft, SalesForce, and Snowflake.'),
('What is the Centre of Excellence (CoE) at Caelius Consulting?', 'The CoE is a professional grooming platform at Caelius Consulting that offers training programs and modules, knowledge sharing sessions, tests, and soft skills enhancement to develop world-class professionals.'),
('Who is the Chief Technology Officer at Caelius Consulting?', 'Mr. Munish Dadwal serves as the Chief Technology Officer at Caelius Consulting.'),
('Who is the Chief Outsourcing Officer at Caelius Consulting?', 'Mr. Mankesh Dadhwal is the Chief Outsourcing Officer at Caelius Consulting.'),
('Who is the Founder & Director of SQE Labs Inc.?', 'Ms. Shilpa Dadhwal is the Founder & Director of SQE Labs Inc.'),
('Who is the VP Customer Success at Caelius Consulting?', 'Ms. Lavina Ambani holds the position of VP Customer Success at Caelius Consulting.'),
('Who is a Partner at Caelius Consulting, Singapore?', 'Mr. Jayadeep Apte is a Partner at Caelius Consulting, Singapore.'),
('Who is the Chief People Officer at Caelius Consulting?', 'Dr. Mukta Dewan serves as the Chief People Officer at Caelius Consulting.'),
('How can I contact Caelius Consulting?', 'You can contact Caelius Consulting at hr@caeliusconsulting.com or visit their website at www.caeliusconsulting.com.'),
('What are the key technologies used in Caelius Consulting?', 'Key technologies used at Caelius Consulting include MuleSoft, SalesForce, and Snowflake.');


INSERT INTO QnA (Query, Result)
VALUES
('What is the capital city of India?', 'Delhi is the capital city of India.'),
('Who wrote the novel "To Kill a Mockingbird"?', 'Harper Lee wrote the novel "To Kill a Mockingbird."'),
('What is the chemical symbol for gold?', 'Au is the chemical symbol for gold.'),
('Which planet is known as the "Red Planet"?', 'Mars is known as the "Red Planet."'),
('Who painted the Mona Lisa?', 'Leonardo da Vinci painted the Mona Lisa.'),
('What is the tallest mountain in the world?', 'Mount Everest is the tallest mountain in the world.'),
('Which country is famous for the Taj Mahal?', 'India is famous for the Taj Mahal.'),
('Who invented the telephone?', 'Alexander Graham Bell invented the telephone.'),
('What is the largest ocean in the world?', 'The Pacific Ocean is the largest ocean in the world.'),
('Which animal is known as the "King of the Jungle"?', 'The Lion is known as the "King of the Jungle."'),
('What is the largest organ in the human body?', 'The Skin is the largest organ in the human body.'),
('What is the currency of Japan?', 'The currency of Japan is the Japanese Yen.'),
('Who wrote the play "Romeo and Juliet"?', 'William Shakespeare wrote the play "Romeo and Juliet."'),
('What is the chemical symbol for iron?', 'Fe is the chemical symbol for iron.'),
('Which country is known as the "Land of the Rising Sun"?', 'Japan is known as the "Land of the Rising Sun."'),
('Who painted the famous artwork "The Starry Night"?', 'Vincent van Gogh painted the famous artwork "The Starry Night."'),
('What is the capital city of Brazil?', 'Brasília is the capital city of Brazil.'),
('Who is the author of the Harry Potter book series?', 'J.K. Rowling is the author of the Harry Potter book series.'),
('What is the largest continent in the world?', 'Asia is the largest continent in the world.'),
('Which planet is known for its beautiful rings?', 'Saturn is known for its beautiful rings.'),
('Who invented the light bulb?', 'Thomas Edison invented the light bulb.'),
('What is the currency of India?', 'The currency of Canada is the India National Rupee.'),
('What is the chemical symbol for silver?', 'Ag is the chemical symbol for silver.'),
('Which country is famous for the Eiffel Tower?', 'France is famous for the Eiffel Tower.'),
('Who painted the famous artwork "The Last Supper"?', 'Leonardo da Vinci painted the famous artwork "The Last Supper."'),
('What is the capital city of South Africa?', 'Pretoria (executive), Cape Town (legislative), Bloemfontein (judicial) are the capital cities of South Africa.'),
('Who is the author of "Pride and Prejudice"?', 'Jane Austen is the author of "Pride and Prejudice."'),
('What is the largest desert in the world?', 'The Sahara Desert is the largest desert in the world.'),
('How many planets do we have in our solar system?', 'There are 8 Planets in our solar system.'),
('Which planet is known as the "Giant Planet"?', 'Jupiter is known as the "Giant Planet."'),
('Who invented the computer?', 'Charles Babbage invented the computer.'),
('What is the currency of Mexico?', 'The currency of Mexico is the Mexican Peso.'),
('Who wrote the play "Hamlet"?', 'William Shakespeare wrote the play "Hamlet."'),
('What is the chemical symbol for hydrogen?', 'H is the chemical symbol for hydrogen.'),
('Which country is known as the "Land Down Under"?', 'Australia is known as the "Land Down Under."'),
('Who painted the famous artwork "The Scream"?', 'Edvard Munch painted the famous artwork "The Scream."'),
('What is the capital city of China?', 'Beijing is the capital city of China.'),
('What is the largest river in the world?', 'The Amazon River is the largest river in the world.'),
('Which planet is known as the "Blue Planet"?', 'Earth is known as the "Blue Planet."'),
('Who invented gravity?', 'Isaac Newton discovered gravity.'),
('What is the currency of Russia?', 'The currency of Russia is the Russian Ruble.'),
('Who wrote the play "Macbeth"?', 'William Shakespeare wrote the play "Macbeth."'),
('What is the chemical symbol for oxygen?', 'O is the chemical symbol for oxygen.'),
('Which country is famous for the Great Wall?', 'China is famous for the Great Wall.'),
('What is the capital city of Germany?', 'Berlin is the capital city of Germany.'),
('Who is the author of "The Catcher in the Rye"?', 'J.D. Salinger is the author of "The Catcher in the Rye."'),
('What is the largest lake in Africa?', 'Lake Victoria is the largest lake in Africa.'),
('Which planet is known as the "Ringed Planet"?', 'Saturn is known as the "Ringed Planet."'),
('Who invented the theory of relativity?', 'Albert Einstein invented the theory of relativity.'),
('What is the currency of France?', 'The currency of France is the Euro (formerly French Franc).'),
('Who wrote the novel "The Lord of the Rings"?', 'J.R.R. Tolkien wrote the novel "The Lord of the Rings."'),
('What is the tallest waterfall in the world?', 'Angel Falls (Venezuela) is the tallest waterfall in the world.'),
('Which planet is known as the "Morning Star" or "Evening Star"?', 'Venus is known as the "Morning Star" or "Evening Star."'),
('Who invented the printing press?', 'Johannes Gutenberg invented the printing press.'),
('What is the currency of Italy?', 'The currency of Italy is the Euro (formerly Italian Lira).'),
('Who wrote the play "Othello"?', 'William Shakespeare wrote the play "Othello."'),
('What is the chemical symbol for carbon?', 'C is the chemical symbol for carbon.'),
('Which country is famous for Mount Fuji?', 'Japan is famous for Mount Fuji.'),
('Who painted the famous artwork "Guernica"?', 'Pablo Picasso painted the famous artwork "Guernica."'),
('What is the capital city of India?', 'New Delhi is the capital city of India.'),
('Who is the author of "The Chronicles of Narnia" book series?', 'C.S. Lewis is the author of "The Chronicles of Narnia" book series.'),
('What is the largest island in the world?', 'Greenland is the largest island in the world.');



SELECT * from QnA;





