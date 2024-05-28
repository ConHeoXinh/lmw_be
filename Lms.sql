-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: organica
-- ------------------------------------------------------
-- Server version	8.0.33
CREATE DATABASE IF NOT EXISTS organica;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'José Mauro de Vasconcelos (1920 - 1984) là nhà văn người Brazil, mang dòng máu Ấn Độ và Bồ Đào Nha. Ông viết văn năm 22 tuổi, đã trải qua khá nhiều công việc trong lĩnh vực thể thao và điện ảnh. Năm 2015, Google Doodle đã kỷ niệm sinh nhật lần thứ 95 của ông.','https://res.cloudinary.com/dboo9wwlk/image/upload/v1704262584/review-sach-cay-cam-ngot-cua-toi-6.jpg.jpg','José Mauro de Vasconcelos'),(2,'Paulo Coelho sinh tại Rio de Janeiro (Brasil), vào trường luật ở đó, nhưng đã bỏ học năm 1970 để du lịch qua Mexico, Peru, Bolivia và Chile, cũng như châu Âu và Bắc Phi. Hai năm sau ông trở về Brasil và bắt đầu soạn lời cho nhạc pop. Ông cộng tác với những nhạc sĩ pop như Raul Seixas. Năm 1974 ông bị bắt giam một thời gian ngắn vì những hoạt động chống lại chế độ độc tài lúc đó ở Brasil. Sách của ông đã được bán ra hơn 86 triệu bản trên 150 nước và được dịch ra 56 thứ tiếng. Ông đã nhận được nhiều giải thưởng của nhiều nước, trong đó tác phẩm Veronika quyết chết (Veronika decide morrer) được đề cử cho Giải Văn chương Dublin IMPAC Quốc tế có uy tín. Tiểu thuyết Nhà giả kim (O Alquimista) của ông, một câu chuyện thấm đẫm chất thơ, đã bán được hơn 65 triệu bản trên thế giới và được dịch ra 56 thứ tiếng, trong đó có tiếng Việt.] Tác phẩm này được dựng thành phim do Lawrence Fishburne sản xuất, vì diễn viên này rất hâm mộ Coelho. Sách của ông còn có Hành hương (O diário de um mago) (được công ty Arxel Tribe lấy làm cơ sở cho một trò chơi vi tính), Bên sông Piedra tôi ngồi xuống và khóc (Na margem do rio Piedra eu sentei e chorei) và Những nữ chiến binh (As Valkírias). Cuốn tiểu thuyết năm 2005 O Zahir của ông bị cấm ở Iran, 1000 bản sách bị tịch thu, nhưng sau đó lại được phát hành.','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704264511/Paulo-Coel.jpg.jpg','Paulo Coelho'),(3,'Niccolò di Bernardo dei Machiavelli là một nhà ngoại giao, nhà triết học và nhà văn thời Phục hưng người Ý, nổi tiếng với tác phẩm Quân vương, được viết vào năm 1513. Ông thường được gọi là cha đẻ của triết học chính trị hiện đại hoặc khoa học chính trị.','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704264990/Niccolo_Machiavelli%27s.jpg.jpg','Niccolò Machiavelli'),(4,'Nguyên Hồng (1918 – 1982). Tên khai sinh của ông là Nguyễn Nguyên Hồng, quê ở thành phố Nam Định. Nguyên Hồng sống chủ yếu ở thành phố cảng Hải Phòng, trong một xóm lao động nghèo.\nÔng có tuổi thơ thiếu thốn tình cảm và vật chất, sinh ra trong gia đình có hoàn cảnh bất hạnh. Ông mồ côi cha từ nhỏ, phải sống với những người cô ruột cay nghiệt. Ngay từ khi còn bé, Nguyên Hồng đã phải lưu lạc, bôn ba cùng mẹ đi khắp nơi để bán hàng kiếm sống. ','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704265550/Nguy%E1%BB%85n_Nguy%C3%AAn_H%E1%BB%93ng.jpg.jpg','Nguyên Hồng'),(5,'Nguyễn Nhật Ánh (sinh ngày 7 tháng 5 năm 1955)[1] là một nam nhà văn người Việt Nam. Được xem là một trong những nhà văn hiện đại xuất sắc nhất Việt Nam hiện nay, ông được biết đến qua nhiều tác phẩm văn học về đề tài tuổi trẻ. Nhiều tác phẩm của ông được độc giả và giới chuyên môn đánh giá cao, đa số đều đã được chuyển thể thành phim.\nÔng lần lượt viết về sân khấu, phụ trách mục tiểu phẩm, phụ trách trang thiếu nhi và hiện nay là bình luận viên thể thao trên báo Sài Gòn Giải phóng Chủ nhật với bút danh Chu Đình Ngạn. Ngoài ra, ông còn có những bút danh khác như Anh Bồ Câu, Lê Duy Cật, Đông Phương Sóc, Sóc Phương Đông.','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704265942/nguyen-nhat-anh-1-8512-1671770487.jpg.jpg','Nguyễn Nhật Ánh'),(6,'hiền sư Thích Nhất Hạnh – Sư Ông Làng Mai – là một bậc thầy hướng dẫn tâm linh có ảnh hưởng lớn trên khắp thế giới. Thiền sư đồng thời là một nhà thơ, một nhà hoạt động cho hòa bình và được nhiều người biết đến qua các bài giảng cũng như qua các cuốn sách nổi tiếng về chánh niệm và về hòa bình. Mục sư Martin Luther King từng gọi Thiền sư là “một tông đồ của hòa bình và bất bạo động” khi đề cử Người cho giải Nobel Hòa bình vào năm 1967. Trong gần 40 năm sống xa quê hương, Thiền sư là một trong những người tiên phong đem đạo Bụt, đặc biệt là pháp môn chánh niệm, đến với xã hội Tây phương và góp phần xây dựng một cộng đồng Phật giáo dấn thân cho thế kỷ XXI.','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704266392/tnhanh-16428292518371815731050.jpeg.jpg','Thích Nhất Hạnh'),(7,'Nguyên Phong là một dịch giả dịch rất nhiều sách Ông tên thật là Vũ Văn Du, sinh năm 1950 tại Hà Nội. Nguyên Phong rời Việt Nam du học ở Mỹ từ năm 1968 và tốt nghiệp cao học ở hai ngành Sinh vật học và Điện toán.\nNgoài công việc chính là một kỹ sư cao cấp tại Boeing trong hơn 20 năm, ông vẫn tiếp tục nghiên cứu trong vai trò nhà khoa học tại Đại học Carnergie Mellon và Đại học Seattle, Mỹ. Ông còn giảng dạy tại một số đại học quốc tế tại Trung Hoa, Hàn Quốc, Nhật Bản, Ấn Độ về lĩnh vực công nghệ phần mềm.\nSong song với vai trò một nhà khoa học, Nguyên Phong còn là dịch giả nổi tiếng của loạt sách về văn hóa và tâm linh phương Đông. Trong số đó, có thể kể đến các ấn phẩm: Hành Trình về phương Đông, Ngọc sáng trong hoa sen, Bên rặng Tuyết sơn, Hoa sen trên tuyết, Hoa trôi trên sóng nước, Huyền thuật và đạo sĩ Tây Tạng, Trở về từ cõi sáng, Minh triết trong đời sống, Đường mây qua xứ tuyết…\nCác tác phẩm dịch thuật về Phật giáo của ông đã được đọc và thâu âm, phổ biến rộng rãi tại hải ngoại, giúp nhiều người phấn khởi bước vào con đường học đạo với tâm rộng mở, hưởng được niềm vui an lạc vô tận của đạo.','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704266783/john-vu-nguyen-phong.jpg.jpg','Nguyên Phong'),(8,'Tác giả Lê Bảo Ngọc là Giám đốc Trung tâm Pháp luật và Văn hóa với một profile “không phải dạng vừa”: chứng chỉ Tâm lý học Tội phạm - Đại học chính pháp Trung Quốc, chứng chỉ Tội phạm học - Đại học Cảnh sát điều tra hình sự Trung Quốc, chứng chỉ chương trình hợp tác kỹ thuật Việt Nam - Australia về Quyền con người. Không chỉ là một nhà văn, tác giả Lê Bảo Ngọc còn là một luật gia, một nhà diễn giả,...Với nhiều kinh nghiệm trong lĩnh vực tâm lý, những tác phẩm của tác giả dường như dễ dàng “nắm thóp” những diễn biến tâm lý phức tạp của con người. Một số tác phẩm tiêu biểu: Không phải sói nhưng cũng đừng là cừu; Chuyện tâm lý trong Phòng pháp lý,...','https://res.cloudinary.com/dboo9wwlk/image/upload/v1700028461/1700028461214_sWBmBcSpsX.jpg','Lê Bảo Ngọc'),(9,'Nhà sư Thích Minh Niệm có tên khai sinh là Lê Quốc Triều, sinh năm 1975 ở huyện Châu Thành, tỉnh Tiền Giang.\nVào năm 1992, tức là khi thầy đã được 17 tuổi, ngay sau khi tốt nghiệp cấp Trung học phổ thông, thầy Thích Minh Niệm chính thức xuất gia tu tập và nghiên cứu tư tưởng Phật giáo Đại thừa tại Phật học viện Huệ Nghiêm (TP Hồ Chí Minh), lấy pháp danh là Minh Niệm. Tại đây, thầy đã được thọ giáo rất nhiều kiến thức sâu rộng của tư tưởng Phật Giáo Đại Thừa, giúp ích thầy rất nhiều trên hành trình tu tập của bản thân.\nTuy nhiên, chỉ ít lâu sau đó thầy đã gặp nỗi mất mát lớn nhất là cả cha lẫn mẹ cùng mất trong một vụ tai nạn. Đứng trước nỗi đau này, thầy Thích Minh Niệm đã nhận thấy rằng bản thân mình dù đã trải qua quá trình tu thân học đạo, thế nhưng lại chưa thực sự bước vào con đường chuyển hóa, vượt qua nỗi đau. \nĐiều này sau đó đã được thầy nhắc đi nhắc lại khá nhiều lần trong các buổi thuyết giảng Phật pháp do thầy tổ chức, để các Phật tử và Tăng ni có thể hiểu được hành trình vượt qua nỗi đau, trước hết là bản thân mình cần phải thực sự đối diện với nó.\nSau khi thầy đã đối diện với nỗi đau lớn đó là mất cùng lúc cả cha lẫn mẹ, sư thầy Thích Minh Niệm quyết định tìm đến với thiền. Thầy đã tiếp cận với thiền ngay từ những bài học cơ bản nhất về Tứ Niệm Xứ của Thiền sư Ajahn Chah. Đây cũng chính là thời điểm mà thầy Thích Minh Niệm cảm thấy bản thân mình giống như đang sống lại từng ngày và bắt đầu quá trình tu hành đích thực mà trước đó thầy chưa từng được trải qua.','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704268268/MinhNiem.jpg.jpg','Minh Niệm'),(10,'John Gray (sinh ngày 28 tháng 12 năm 1951) là một cố vấn, giảng viên và tác giả người Mỹ. Năm 1969, ông bắt đầu một liên hệ kéo dài 9 năm với Maharishi Mahesh Yogi trước khi bắt đầu sự nghiệp với tư cách là một tác giả và cố vấn quan hệ cá nhân. Năm 1992, ông xuất bản cuốn sách Men Are from Mars, Women Are from Venus, trở thành cuốn sách bán chạy nhất trong thời gian dài và hình thành chủ đề trung tâm của tất cả các cuốn sách và hoạt động nghề nghiệp tiếp theo của ông. Sách của ông đã bán được hàng triệu bản.','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704268677/Gray.jpg.jpg','John Gray'),(11,'Albert Rutherford là một chuyên gia tâm lý học, một nhà nghiên cứu tư duy hệ thống và tư duy phản biện người Mỹ. Ông dành gần như trọn sự nghiệp của mình để nghiên cứu khoa học liên quan đến tư duy của não bộ, những ảnh hưởng tích cực của việc hình thành lối tư duy phản biện và cách làm thế nào để rèn luyện tư duy phản biện và tư duy hệ thống ở mỗi cá nhân. Vì vậy, những đóng góp của ông được đông đảo độc giả và khán giá đón nhận trên khắp thế giới. Về sự nghiệp viết lách, Albert Rutherford chuyên tâm xuất bản nhiều cuốn sách về tâm lý học và tư duy. Trong đó có những cuốn sách bán chạy trên Amazon, New York Times,… Albert Rutherford cũng là một huấn luyện viên về tư duy phản biện và diễn giả được yêu thích tại Mỹ.','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704269320/albert-rutherford.jpg.jpg','albert rutherford'),(12,'Là một tác giả đồng thời là một nhà sản xuất. Ông từng là một nhân viên bình thường, từng làm bán hàng rồi tự mở công ty, ông đã đi nhiều nơi, đọc sách, sáng tác, tìm hiểu về cuộc sống. Vốn sống phong phú, bút pháp tinh tế cùng lối viết đi thẳng vào trọng tâm luôn mang lại cho độc giả cảm giác sảng khoái khi đọc tác phẩm của ông. Một số tác phẩm của ông đã được xuất bản như “Khoa triết học Đại học Bắc Kinh”, “EQ cao chính là biết cách nói chuyện”.','https://res.cloudinary.com/dboo9wwlk/image/upload/v1700028461/1700028461214_sWBmBcSpsX.jpg','Trương Tiếu Hằng'),(13,'Barry J. Nalebuff (sinh ngày 11 tháng 7 năm 1958) là một doanh nhân, nhà lý luận kinh doanh và nhà văn người Mỹ. Ông là Giáo sư Quản lý Milton Steinbach tại Trường Quản lý Yale và là tác giả chuyên về chiến lược kinh doanh và lý thuyết trò chơi . Những cuốn sách đã xuất bản của ông bao gồm Tư duy chiến lược và Nghệ thuật chiến lược. Lớp học về đàm phán của Nalebuff có hơn 67.000 người học tích cực thông qua Coursera và có điểm quảng bá ròng cao thứ hai trên nền tảng này. Ông ấy có một chuyên mục bán thường xuyên trên Forbes cùng với Ian Ayres có tên \"Tại sao không?\". \n Nalebuff cũng có nhiều dự án kinh doanh. Ông là người đồng sáng lập của Honest Tea và Kombrewcha. Ông phục vụ trong hội đồng quản trị của Q Drinks (do học trò cũ của ông là Jordan Silbert thành lập), Calicraft Beer và AGP Glass.','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704270083/barry_nalebuff-196x300.jpg.jpg','Barry J. Nalebuff'),(14,'Avinash Kamalakar Dixit (sinh ngày 6 tháng 8 năm 1944) là một nhà kinh tế học người Mỹ gốc Ấn . Ông là Giáo sư danh dự về Kinh tế của Đại học John JF Sherrerd \'52 tại Đại học Princeton , và là Giáo sư phụ trợ xuất sắc về Kinh tế tại Đại học Lĩnh Nam (Hồng Kông) , nghiên cứu viên cao cấp tại Cao đẳng Nuffield, Oxford và Sanjaya Lall Nghiên cứu viên thỉnh giảng cao cấp tại Green Templeton College, Oxford','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704270430/avinash-dixit.jpeg.jpg','Avinash Dixit'),(15,'Dale Breckenridge Carnegie (trước kia là Carnagey cho tới năm 1922 và có thể một thời gian muộn hơn) (24 tháng 11 năm 1888 – 1 tháng 11 năm 1955) là một nhà văn và nhà thuyết trình Mỹ và là người phát triển các lớp tự giáo dục, nghệ thuật bán hàng, huấn luyện đoàn thể, nói trước công chúng và các kỹ năng giao tiếp giữa mọi người. Ra đời trong cảnh nghèo đói tại một trang trại ở Missouri, ông là tác giả cuốn Đắc Nhân Tâm, được xuất bản lần đầu năm 1936, một cuốn sách hàng bán chạy nhất và được biết đến nhiều nhất cho đến tận ngày nay, nội dung nói về cách ứng xử, cư xử trong cuộc sống. Ông cũng viết một cuốn tiểu sử Abraham Lincoln, với tựa đề Lincoln con người chưa biết, và nhiều cuốn sách khác.\nCarnegie là một trong những người đầu tiên đề xuất cái ngày nay được gọi là đảm đương trách nhiệm, dù nó chỉ được đề cập tỉ mỉ trong tác phẩm viết của ông. Một trong những ý tưởng chủ chốt trong những cuốn sách của ông là có thể thay đổi thái độ của người khác khi thay đổi sự đối xử của ta với họ.','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704270868/a3hj.jpg.jpg','Dale Carnegie'),(16,'Không có thông tin tác giả','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704271600/51fX55cUD5L._SY600_.jpg.jpg','Darrell Mullis'),(17,'Judith Orloff (sinh ngày 25 tháng 6 năm 1951) là một bác sĩ tâm thần người Mỹ được hội đồng chứng nhận , người tự cho mình là nhà thấu thị ( nhà ngoại cảm ), và là tác giả của năm cuốn sách.\nOrloff sinh ra ở Philadelphia , Pennsylvania, con gái của Theodore và Maxine, cả hai đều là bác sĩ. Cô lớn lên ở Beverly Hills , nơi sau này cô nói rằng cô đã trải qua trực giác mạnh mẽ khi còn nhỏ. Gia đình bà có nhiều thầy thuốc. Orloff nhận bằng Bác sĩ Y khoa (MD) từ Đại học Nam California vào năm 1979. Cô đã hoàn thành khóa thực tập y khoa tại Bệnh viện Cựu chiến binh Wadsworth ở Los Angeles vào năm 1980 và có nội trú tâm thần tại UCLA Viện Tâm thần kinh từ 1979 đến 1983.','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704271668/Dr.-Judith-Red-one-v4CroppedCredit.jpg.jpg','Judith Orloff'),(18,'Walter Seff Isaacson (sinh ngày 20 tháng 5 năm 1952) là một tác giả, nhà báo và giáo sư người Mỹ. Ông từng là chủ tịch và giám đốc điều hành của Viện Aspen , một tổ chức nghiên cứu chính sách phi đảng phái có trụ sở tại Washington, DC , chủ tịch và giám đốc điều hành của CNN , đồng thời là biên tập viên của tờ Time .\nSinh ra ở New Orleans , Louisiana , ông theo học tại Đại học Harvard và Cao đẳng Pembroke, Oxford với tư cách là học giả Rhodes . Ông là đồng tác giả với Evan Thomas của The Wise Men: Six Friends and the World They Made (1986) và là tác giả của Pro and Con (1983), Kissinger: A Biography (1992), Benjamin Franklin: An American Life ( 2003), Einstein: His Life and Universe (2007), American Sketches (2009), Steve Jobs (2011), The Innovators: How a Group of Hackers, Geniuses, and Geeks Created the Digital Revolution (2014), Leonardo da Vinci ( 2017), Người phá mã: Jennifer Doudna, Chỉnh sửa gen và Tương lai của loài người (2021) và Elon Musk (2023).\nIsaacson là giáo sư tại Đại học Tulane và là đối tác tư vấn tại Perella Weinberg Partners , một công ty dịch vụ tài chính có trụ sở tại Thành phố New York. Ông là phó chủ tịch của Cơ quan Phục hồi Louisiana , cơ quan giám sát việc tái thiết sau Bão Katrina , chủ tịch hội đồng chính phủ điều hành Đài Tiếng nói Hoa Kỳ , và là thành viên của Ban Đổi mới Quốc phòng.','https://res.cloudinary.com/dboo9wwlk/image/upload/v1700028461/1700028461214_sWBmBcSpsX.jpg','Walter Isaacson'),(19,'Nhiều Tác Giả','https://res.cloudinary.com/dboo9wwlk/image/upload/v1700028461/1700028461214_sWBmBcSpsX.jpg','Nhiều Tác Giả'),(20,'Không có thông tin tác giả','https://res.cloudinary.com/dboo9wwlk/image/upload/v1700028461/1700028461214_sWBmBcSpsX.jpg','ThS Phan Hoàng Văn'),(21,'Song Hong Binh (Tống Hồng Binh) sinh năm 1968 tại Tứ Xuyên, Trung Quốc. Ông là tác giả của rất nhiều cuốn sách bán chạy, là học giả nghiên cứu tài chính thế giới và cũng là Viện trưởng của Viện nghiên cứu Kinh tế Tài chính Hoàn cầu (Bắc Kinh). ','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704273313/song-hong-bing.jpg.jpg','Song Hong Bing'),(22,'asdh cba zxcbas c\nzxcnxzc','http://res.cloudinary.com/dboo9wwlk/image/upload/v1702008621/og-image.png.png','long'),(23,'ád','http://res.cloudinary.com/dboo9wwlk/image/upload/v1702008621/og-image.png.png','longkia'),(24,'sdà scxzvxc','http://res.cloudinary.com/dboo9wwlk/image/upload/v1702008621/og-image.png.png','xzcxzcéà');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `copies` int NOT NULL,
  `copies_available` int NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `description` longtext,
  `for_user` bit(1) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `page` int NOT NULL,
  `price` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `publisher_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgtvt7p649s4x80y6f4842pnfq` (`publisher_id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,0,20,'2024-01-03 13:42:55','Có hề gì đâu bao nhiêu là hắt hủi, đánh mắng, vì Zezé đã có một người bạn đặc biệt để trút nỗi lòng: cây cam ngọt nơi vườn sau. Và cả một người bạn nữa, bằng xương bằng thịt, một ngày kia xuất hiện, cho cậu bé nhạy cảm khôn sớm biết thế nào là trìu mến, thế nào là nỗi đau, và mãi mãi thay đổi cuộc đời cậu. Mở đầu bằng những thanh âm trong sáng và kết thúc lắng lại trong những nốt trầm hoài niệm, Cây cam ngọt của tôi khiến ta nhận ra vẻ đẹp thực sự của cuộc sống đến từ những điều giản dị như bông hoa trắng của cái cây sau nhà, và rằng cuộc đời thật khốn khổ nếu thiếu đi lòng yêu thương và niềm trắc ẩn. Cuốn sách kinh điển này bởi thế không ngừng khiến trái tim người đọc khắp thế giới thổn thức, kể từ khi ra mắt lần đầu năm 1968 tại Brazil.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704264065/image_217480.jpg.jpg','vietnamese',244,2000,'Cây Cam Ngọt Của Tôi',1),(2,2,28,'2024-01-03 13:53:05','Tất cả những trải nghiệm trong chuyến phiêu du theo đuổi vận mệnh của mình đã giúp Santiago thấu hiểu được ý nghĩa sâu xa nhất của hạnh phúc, hòa hợp với vũ trụ và con người. \nTiểu thuyết Nhà giả kim của Paulo Coelho như một câu chuyện cổ tích giản dị, nhân ái, giàu chất thơ, thấm đẫm những minh triết huyền bí của phương Đông. Trong lần xuất bản đầu tiên tại Brazil vào năm 1988, sách chỉ bán được 900 bản. Nhưng, với số phận đặc biệt của cuốn sách dành cho toàn nhân loại, vượt ra ngoài biên giới quốc gia, Nhà giả kim đã làm rung động hàng triệu tâm hồn, trở thành một trong những cuốn sách bán chạy nhất mọi thời đại, và có thể làm thay đổi cuộc đời người đọc.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704264606/NhaGiaKim.jpg.jpg','vietnamese',227,2000,'Nhà Giả Kim (Tái Bản 2020)',1),(3,3,18,'2024-01-03 13:59:54','Quả thật, hiếm có cuốn sách nào thực sự “vượt thời gian” như Quân vương, cuốn sách nhỏ khiêm nhường song vô giá của Niccolo Machiavelli. Kể cả ngày nay, và kể cả dù bản thân ta không phải một quân vương hay nhà cai trị, ta vẫn có thể rút ra từ cuốn sách này những lời khuyên đầy minh triết về thuật đối nhân xử thế, có thể dựa vào cuốn sách này để hiểu hơn về động cơ ẩn sau những động thái của các chính trị gia hiện đại - tức là, dẫu ta chỉ là một thường dân “hèn mọn”, ta vẫn có tầm nhìn thấu suốt của một đấng quân vương.',_binary '','https://res.cloudinary.com/dboo9wwlk/image/upload/v1704273729/image_181756_xtfhnr.jpg','vietnamese',215,1000,'Quân Vương (Tái Bản 2018)',2),(4,1,33,'2024-01-03 14:08:11','Những ngày thơ ấu có thể coi là một tác phẩm xuất sắc. Đây là tập hồi ký về tuổi thơ ghi lại những “rung động cực điểm của một linh hồn trẻ dại”.\nCuốn sách bao gồm những phần chính sau:\n- Tiếng kèn\n- Chúa thương xót chúng con\n- Truỵ lạc\n- Trong lòng mẹ\n- Đêm noel',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704265672/image_182317.jpg.jpg','vietnamese',120,1000,'Những Ngày Thơ Ấu (Tái Bản 2019)',3),(5,6,47,'2024-01-03 14:14:31','Ta bắt gặp trong Tôi Thấy Hoa Vàng Trên Cỏ Xanh một thế giới đấy bất ngờ và thi vị non trẻ với những suy ngẫm giản dị thôi nhưng gần gũi đến lạ. Câu chuyện của Tôi Thấy Hoa Vàng Trên Cỏ Xanh có chút này chút kia, để ai soi vào cũng thấy mình trong đó, kiểu như lá thư tình đầu đời của cu Thiều chẳng hạ ngây ngô và khờ khạo.\nNhưng Tôi Thấy Hoa Vàng Trên Cỏ Xanh hình như không còn trong trẻo, thuần khiết trọn vẹn của một thế giới tuổi thơ nữa. Cuốn sách nhỏ nhắn vẫn hồn hậu, dí dỏm, ngọt ngào nhưng lại phảng phất nỗi buồn, về một người cha bệnh tật trốn nhà vì không muốn làm khổ vợ con, về một người cha khác giả làm vua bởi đứa con tâm thầm của ông luôn nghĩ mình là công chúa, Những bài học về luân lý, về tình người trở đi trở lại trong day dứt và tiếc nuối.\nTôi Thấy Hoa Vàng Trên Cỏ Xanh lắng đọng nhẹ nhàng trong tâm tưởng để rồi ai đã lỡ đọc rồi mà muốn quên đi thì thật khó.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704266053/nna-hvtcx.jpg.jpg','vietnamese',378,1000,'Tôi Thấy Hoa Vàng Trên Cỏ Xanh (Tái Bản 2023)',4),(6,7,52,'2024-01-03 14:22:33','Nhiều người trong chúng ta tin rằng cuộc đời của ta bắt đầu từ lúc chào đời và kết thúc khi ta chết. Chúng ta tin rằng chúng ta tới từ cái Không, nên khi chết chúng ta cũng không còn lại gì hết. Và chúng ta lo lắng vì sẽ trở thành hư vô.\nBụt có cái hiểu rất khác về cuộc đời. Ngài hiểu rằng sống và chết chỉ là những ý niệm không có thực. Coi đó là sự thực, chính là nguyên do gây cho chúng ta khổ não. Bụt dạy không có sinh, không có diệt, không tới cũng không đi, không giống nhau cũng không khác nhau, không có cái ngã thường hằng cũng không có hư vô. Chúng ta thì coi là Có hết mọi thứ. Khi chúng ta hiểu rằng mình không bị hủy diệt thì chúng ta không còn lo sợ. Đó là sự giải thoát. Chúng ta có thể an hưởng và thưởng thức đời sống một cách mới mẻ\nKhông diệt Không sinh Đừng sợ hãi là tựa sách được Thiền sư Thích Nhất Hạnh viết nên dựa trên kinh nghiệm của chính mình. Ở đó, Thầy Nhất Hạnh đã đưa ra một thay thế đáng ngạc nhiên cho hai triết lý trái ngược nhau về vĩnh cửu và hư không: “Tự muôn đời tôi vẫn tự do. Tử sinh chỉ là cửa ngõ ra vào, tử sinh là trò chơi cút bắt. Tôi chưa bao giờ từng sinh cũng chưa bao giờ từng diệt” và “Nỗi khổ lớn nhất của chúng ta là ý niệm về đến-đi, lui-tới.”',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704266530/8935278607311.jpg.jpg','vietnamese',224,1000,'Không Diệt Không Sinh Đừng Sợ Hãi (Tái Bản 2022)',5),(7,3,75,'2024-01-03 14:31:09','“Muôn kiếp nhân sinh” là tác phẩm do Giáo sư John Vũ - Nguyên Phong viết từ năm 2017 và hoàn tất đầu năm 2020 ghi lại những câu chuyện, trải nghiệm tiền kiếp kỳ lạ từ nhiều kiếp sống của người bạn tâm giao lâu năm, ông Thomas – một nhà kinh doanh tài chính nổi tiếng ở New York. Những câu chuyện chưa từng tiết lộ này sẽ giúp mọi người trên thế giới chiêm nghiệm, khám phá các quy luật về luật Nhân quả và Luân hồi của vũ trụ giữa lúc trái đất đang gặp nhiều tai ương, biến động, khủng hoảng từng ngày.\n“Muôn kiếp nhân sinh” là một bức tranh lớn với vô vàn mảnh ghép cuộc đời, là một cuốn phim đồ sộ, sống động về những kiếp sống huyền bí, trải dài từ nền văn minh Atlantis hùng mạnh đến vương quốc Ai Cập cổ đại của các Pharaoh quyền uy, đến Hợp Chủng Quốc Hoa Kỳ ngày nay.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704266929/muonkiepnhansinh_1.jpg.jpg','vietnamese',481,3000,'Sách Muôn Kiếp Nhân Sinh (Bìa Mềm) - Nguyên Phong',6),(8,2,62,'2024-01-03 14:43:28','SÓI VÀ CỪU - BẠN KHÔNG TỐT NHƯ BẠN NGHĨ ĐÂU!\nLàn ranh của việc ngây thơ hay xấu xa đôi khi mỏng manh hơn bạn nghĩ.\nBạn làm một việc mà mình cho là đúng, kết quả lại bị mọi người khiển trách.\nBạn ủng hộ một quan điểm của ai đó, và số đông khác lại ủng hộ một quan điểm trái chiều.\nRốt cuộc thì bạn sai hay họ sai?\nCuốn sách “Không phải sói nhưng cũng đừng là cừu” đến từ tác giả Lê Bảo Ngọc sẽ giúp bạn hiểu rõ hơn những khía cạnh sắc sảo phía sau những nhận định đúng, sai đơn thuần của mỗi người.\n\nNhững câu hỏi đầy tính tranh cãi như “Cứu người hay cứu chó?”, “Một kẻ thô lỗ trong lớp vỏ “thẳng tính” khác với người EQ thấp như thế nào?”, “Vì sao một bộ phận nữ giới lại victim blaming đối với nạn nhân bị xâm hại?”, được tác giả đưa ra trong “Không phải sói nhưng cũng đừng là cừu”. Bằng từng chương sách của mình, tác giả vạch rõ cho bạn rằng “thật sự thế nào mới là người tốt”, ngây thơ và xấu xa có ranh giới rõ ràng như thế không, rốt cuộc bạn có là người tốt như mình vẫn luôn nghĩ?',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704267692/_khong-phai-soi-nhung-cung-dung-la-cuu.jpg.jpg','vietnamese',296,4000,'Không Phải Sói Nhưng Cũng Đừng Là Cừu',5),(9,9,57,'2024-01-03 14:56:22','Hiểu Về Trái Tim – Cuốn Sách Mở Cửa Thề Giới Cảm Xúc Của Mỗi Người\nXuất bản lần đầu tiên vào năm 2011, Hiểu Về Trái Tim trình làng cũng lúc với kỷ lục: cuốn sách có số lượng in lần đầu lớn nhất: 100.000 bản. Trung tâm sách kỷ lục Việt Nam công nhận kỳ tích ấy nhưng đến nay, con số phát hành của Hiểu về trái tim vẫn chưa có dấu hiệu chậm lại.\nLà tác phẩm đầu tay của nhà sư Minh Niệm, người sáng lập dòng thiền hiểu biết (Understanding Meditation), kết hợp giữa tư tưởng Phật giáo Đại thừa và Thiền nguyên thủy Vipassana, nhưng Hiểu Về Trái Tim không phải tác phẩm thuyết giáo về Phật pháp. Cuốn sách rất “đời” với những ưu tư của một người tu nhìn về cõi thế. Ở đó, có hạnh phúc, có đau khổ, có tình yêu, có cô đơn, có tuyệt vọng, có lười biếng, có yếu đuối, có buông xả… Nhưng, tất cả những hỉ nộ ái ố ấy đều được khoác lên tấm áo mới, một tấm áo tinh khiết và xuyên suốt, khiến người đọc khi nhìn vào, đều thấy mọi sự như nhẹ nhàng hơn…',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704268479/z4118763446785_cf4bc22d353b065bbb37e686de1f9207.jpg.jpg','vietnamese',479,2000,'Hiểu Về Trái Tim (Tái Bản 2023)',6),(10,3,84,'2024-01-03 15:03:59','Cuốn sách này thực sự đã giúp đỡ cho hàng triệu độc giả, trong đó có tôi và chắc chắn cũng sẽ có bạn. Nếu không có những ý niệm mới mẻ này thì chưa chắc tôi đã có được cuộc hôn nhân hạnh phúc như hiện nay hay có thể trở thành một người cha giàu đức hy sinh đối với các con của mình như vậy. Những vướng mắc trong mối quan hệ với vợ cách đây hai mươi năm đã từng làm tôi tức điên lên, hiện giờ thi thoảng nó vẫn thường xảy ra. Nhưng điều khác biệt là tôi đã biết khoan dung hơn, chấp nhận và thấu hiểu hơn. Tôi có thể hiểu những lời lẽ và phản ứng của vợ tôi theo cách khách quan hơn, đồng thời tôi biết cách nên đáp lại như thế nào. Có thể tôi là một chuyên gia trong lĩnh vực giao tiếp và sự khác biệt về giới tính, tuy nhiên, đối với Bonnie và các cô con gái của tôi thì việc để hiều được họ vẫn còn là những bí ẩn. Dù vậy, cuốn sách này có thể giúp chúng ta trở nên khoan dung và biết tha thứ khi ai đó không đáp lại theo cách mà ta mong đợi. May mắn thay, để xây dựng những mối quan hệ bền đẹp, tính hoàn hảo không phải là điều kiện bắt buộc.\n\nVới những áp lực công việc ngày càng gia tăng, cùng với những đòi hỏi nhiều hơn về sự lãng mạn trong gia đình, ngày nay những mối quan hệ dường như đang thách thức hầu hết mọi người. Hiểu được người bạn đời của mình tới từ đâu sẽ giúp mối quan hệ của bạn trở nên nhẹ nhàng hơn. Bao dung với những điều khác biệt giữa hai người không có nghĩa là chấp nhận một cách thụ động mối quan hệ đầy vấn đề hoặc thiếu cảm xúc đam mê. Thay vào đó, sự thích nghi lành mạnh này dựa trên nền tảng thấu hiểu thực sự, điều đó sẽ giúp chúng ta cảm thông với người bạn đời hơn, đối đáp với họ bằng nhiều tình yêu hơn và truyền cảm hứng tốt nhất đến với họ. Bạn không thể và cũng không nên cố thay đổi người bạn đời của mình. Thay đổi là việc của họ, còn việc của bạn là thay đổi cách giao tiếp, phản ứng và đối đáp với người bạn đời của mình. Bằng sự thấu hiểu mới mẻ này, bạn sẽ có thêm sức mạnh và sự thông thái để điều chỉnh cách tiếp cận của mình. Từ việc giao tiếp tốt hơn, bạn sẽ biết cách trợ giúp hiệu quả hơn, đồng thời bạn sẽ thành công hơn khi nhận được sự trợ giúp mà bạn muốn.\n\nCó nhiều người áp dụng sai những khái niệm trong cuốn sách này. Họ dùng ví dụ và giải thích này của tôi để bào chữa cho việc không chịu thay đổi những khía cạnh quan trọng giúp mối quan hệ trở nên tốt đẹp hơn. Chẳng hạn, tôi chỉ ra rằng đàn ông cần chui vào chiếc kén của mình thường xuyên để thư giãn mỗi ngày. Tuy nhiên điều này không thể trở thành lý do biện minh cho việc ở lì trong đó cả ngày. Mặt khác, tôi cũng chỉ ra rằng phụ nữ nói chung thường có nhu cầu chia sẻ cảm xúc nhiều hơn đàn ông, như là một cách để đương đầu với sự căng thẳng của mình. Điều này không có nghĩa là họ có thể nói không ngừng hoặc mong chờ đàn ông phải dừng việc của họ lại để lắng nghe bất kỳ điều gì cô ấy nói hay bất cứ khi nào cô ấy muốn. Thật không may là nhiều khi những ý niệm tốt cũng có thể bị áp dụng sai. Nhưng nếu bạn đang tìm cách dùng những ý niệm này để hiểu thêm về người bạn đời của mình, để tôn trọng người khác theo cách mà họ cho là quan trọng, cũng như để người khác hiểu được những nhu cầu của mình thì cuốn sách này có thể giúp ích cho bạn.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704268997/image_183259.jpg.jpg','vietnamese',488,4000,'Đàn Ông Sao Hỏa Đàn Bà Sao Kim',7),(11,3,60,'2024-01-03 15:12:49','Như bạn có thể thấy, chìa khóa để trở thành một người có tư duy phản biện tốt chính là sự tự nhận thức. Bạn cần phải đánh giá trung thực những điều trước đây bạn nghĩ là đúng, cũng như quá trình suy nghĩ đã dẫn bạn tới những kết luận đó. Nếu bạn không có những lý lẽ hợp lý, hoặc nếu suy nghĩ của bạn bị ảnh hưởng bởi những kinh nghiệm và cảm xúc, thì lúc đó hãy cân nhắc sử dụng tư duy phản biện! Bạn cần phải nhận ra được rằng con người, kể từ khi sinh ra, rất giỏi việc đưa ra những lý do lý giải cho những suy nghĩ khiếm khuyết của mình. Nếu bạn đang có những kết luận sai lệch này thì có một sự thật là những đức tin của bạn thường mâu thuẫn với nhau và đó thường là kết quả của thiên kiến xác nhận, nhưng nếu bạn biết điều này, thì bạn đã tiến gần hơn tới sự thật rồi!\nNhững người tư duy phản biện cũng biết rằng họ cần thu thập những ý tưởng và đức tin của mọi người. Tư duy phản biện không thể tự nhiên mà có.\nNhững người khác có thể đưa ra những góc nhìn khác mà bạn có thể chưa bao giờ nghĩ tới, và họ có thể chỉ ra những lỗ hổng trong logic của bạn mà bạn đã hoàn toàn bỏ qua. Bạn không cần phải hoàn toàn đồng ý với ý kiến của những người khác, bởi vì điều này cũng có thể dẫn tới những vấn đề liên quan đến thiên kiến, nhưng một cuộc thảo luận phản biện là một bài tập tư duy cực kỳ hiệu quả.\nViệc lắng nghe những ý kiến của người khác cũng có thể giúp bạn nhận ra rằng phạm vi tri thức của bạn không phải là vô hạn. Không ai có thể biết hết tất cả mọi thứ. Nhưng với việc chia sẻ và đánh giá phê bình kiến thức, chúng ta có thể mở rộng tâm trí. Nếu điều này khiến bạn cảm thấy không thoải mái, không sao cả. Trên thực tế, bước ra ngoài vùng an toàn là một điều quan trọng để mở rộng niềm tin và suy nghĩ của bạn. Tư duy phản biện không phải là chỉ biết vài thứ, và chắc chắn không phải việc xác nhận những điều bạn đã biết. Thay vào đó, nó xoay quanh việc tìm kiếm sự thật – và biến chúng trở thành thứ bạn biết.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704269520/image_195509_1_18448.jpg.jpg','vietnamese',204,4000,'Rèn Luyện Tư Duy Phản Biện',8),(12,0,33,'2024-01-03 15:18:22','Nội dung quyển sách này xoay quanh hai vấn đề đó là “biết cách nói chuyện” và “biết giữ miệng”, thông qua 12 chương sách nói rõ cách nói chuyện với những người khác nhau, cách nói chuyện trong những trường hợp khác nhau, làm thế nào để nắm vững những kỹ năng và chừng mực để nói chuyện cho khôn khéo, những người không giỏi ăn nói làm cách nào mới có thể nói được những lời thích hợp với đúng người và đúng thời điểm, để có thể ứng phó với những trường hợp khác nhau trong giao tiếp.\n\nNgười biết nói chuyện, ẩn sau con người họ là lòng tốt đã khắc sâu vào xương tủy, là sự tôn trọng đến từ việc đặt mình vào vị trí của người khác, là trí tuệ sâu sắc, độc đáo và lòng kiên nhẫn không ngại phiền hà. Họ chưa hẳn là những người giỏi ăn nói, nhưng mỗi khi nói đều làm người khác như được tắm trong gió xuân, vừa mở miệng là đã toát lên khí chất hơn người. \n\nNgười biết giữ miệng, bất kể trong trường hợp nào, họ đều có thể lập tức nhìn thấu cảm xúc của người khác, quan tâm đến cảm giác của đối phương, nói năng có chừng mực, làm gì cũng chừa lại đường lui cho mình và người khác. Vừa mở miệng là có thể làm yên lòng người khác, tự nhiên đi tới đâu cũng sẽ được chào đón.\nBiết giữ im lặng thì cuộc sống sẽ dễ chịu hơn, học cách giữ miệng thì cuộc đời này sẽ không còn điều gì phải hối hận. Điều không nên nói thì không nói, điều không nên hỏi thì không hỏi, hiểu ý mà không vạch trần, nhìn thấu mà không nói ra, đó là bậc đại trí.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704269796/im-lang-la-tri-tue.jpg.jpg','vietnamese',450,3000,'Nói Chuyện Là Bản Năng, Giữ Miệng Là Tu Dưỡng, Im Lặng Là Trí Tuệ (Tái bản 2022)',9),(13,0,23,'2024-01-03 15:32:14','Có phải những người chiến thắng các chương trình truyền hình thực tế được trời phú cho trí thông minh và kỹ năng hơn người?\nCó phải các nhà đầu tư vĩ đại có thể nhìn thấy những điều mà hầu hết mọi người bỏ lỡ?\nCó phải các tay chơi poker sở hữu những tài năng mà chúng ta không có?\nCâu trả lời cho tất cả những câu hỏi trên là \"Không hề!\" Họ hoàn toàn \"bình thường\", như bạn, như tôi hay bất cứ ai ngoài kia.\nThông qua Nghệ thuật tư duy chiến lược, bạn sẽ thấy \"những người thành công\" trong mọi lĩnh vực từ giải trí đến chính trị, từ giáo dục đến thể thao, đạt thành công vang dội là nhờ luôn nắm vững lý thuyết trò chơi hay nghệ thuật tư duy chiến lược, với khả năng dự đoán những động thái tiếp theo của người cùng chơi, trong khi biết rõ rằng đối thủ đang cố gắng làm điều tương tự với mình.\nNgoài ra, bạn cũng sẽ nắm được những bí kíp vô cùng giản đơn để có thể áp dụng lý thuyết trò chơi vào cuộc sống lẫn công việc, từ đó sở hữu một cuộc đời đáng sống.\nGiá sản phẩm trên Tiki đã bao gồm thuế theo luật hiện hành. Bên cạnh đó, tuỳ vào loại sản phẩm, hình thức và địa chỉ giao hàng mà có thể phát sinh thêm chi phí khác như phí vận chuyển, phụ phí hàng cồng kềnh, thuế nhập khẩu (đối với đơn hàng giao từ nước ngoài có giá trị trên 1 triệu đồng).....',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704270715/nghe-thuat-tu-duy-chien-luoc.jpg.jpg','vietnamese',560,5000,'Nghệ Thuật Tư Duy Chiến Lược',10),(14,0,41,'2024-01-03 15:39:27','Đắc Nhân Tâm là nghệ thuật thu phục lòng người, là làm cho tất cả mọi người yêu mến mình. “Đắc Nhân Tâm” cần được cảm nhận bằng sự hiểu rõ bản thân, thành thật với chính mình, hiểu biết và quan tâm đến những người xung quanh để nhìn ra, khơi gợi những tiềm năng ẩn khuất nơi họ và giúp họ phát triển lên một tầm cao mớ Dưới ngòi bút của dịch giả Nguyễn Hiến Lê, bản dịch truyền tải đúng linh hồn của bản gốc nhưng vẫn thân thuộc, gần gũi với bao nhiêu thế hệ và giúp hàng triệu người Việt Nam thành công. Với phiên bản mới nhất này có sửa chữa và thêm 2 chương mới phù hợp với nhu cầu thực tiễn hiện nay đồng thời thêm cả phụ lục của dịch giả Nguyễn Hiến Lê chép một số cố sự của phương Đông dẫn chứng minh họa cho cuốn sách được đầy đủ và hoàn hảo nhất. Vì thế đây chính là cuốn sách mà mọi thế hệ người Việt Nam đều cần có, xứng đáng đứng top Bestseller.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704271008/new_doc_2018-08-21_14.43.16_10.jpg.jpg','vietnamese',450,2000,'Đắc Nhân Tâm - Bí Quyết Để Thành Công',7),(15,1,16,'2024-01-03 15:50:42','Đã bao lần bạn cầm trên tay bảng báo cáo tài chính doanh nghiệp của mình, nhưng chẳng thể nào hiểu nổi? \nKế toán và tài chính là nỗi đau chung của rất nhiều doanh nghiệp nhỏ. Ngôn ngữ tài chính dường như là điều bí ẩn nhất của thế giới. Vô số tính toán và ý đồ được cài cắm sau các con số, mà thậm chí người kinh doanh nhiều năm cũng không thể nào bóc tách nổi.\nNếu bạn vẫn cảm thấy mù mờ với bảng báo cáo tài chính của mình thì thật là đáng tiếc. Tài chính được xem như là ngôn ngữ của kinh doanh. Bảng kế toán sẽ cho bạn biết được doanh nghiệp của mình lời hay lỗ, trả lời câu hỏi vì sao trông bạn có vẻ đang ăn nên làm ra, nhưng két sắt công ty không có lấy một đồng.',_binary '','https://res.cloudinary.com/dboo9wwlk/image/upload/v1705716585/ketoanvia_iunjxa.jpg','vietnamese',268,3000,'Kế Toán Vỉa Hè - Thực Hành Báo Cáo Tài Chính Căn Bản Từ Quầy Bán Nước Chanh',5),(16,1,12,'2024-01-03 15:56:50','Tiểu sử Elon Musk là cuộc khám phá về cuộc sống và công việc của một biểu tượng thời hiện đại, mang đến những hiểu biết sâu sắc về tâm trí của một người có tầm nhìn được thúc đẩy bởi sự theo đuổi không ngừng đổi mới và tiến bộ. Đây là một cuốn sách hấp dẫn dành cho những ai quan tâm đến sự giao thoa giữa công nghệ, tinh thần kinh doanh và tâm lý con người.\nTiểu sử Elon Musk của Walter Isaacson đi sâu vào cuộc đời và tính cách của một trong những nhà đổi mới hấp dẫn và gây tranh cãi nhất trong thời đại chúng ta, Elon Musk. Cuốn tiểu sử đưa người đọc vào một hành trình gần gũi xuyên suốt cuộc đời đầy biến động của Musk, khám phá những trải nghiệm thời thơ ấu của anh ở Nam Phi, mối quan hệ phức tạp của anh với cha mình và quá trình phát triển thành một doanh nhân có tầm nhìn xa trông rộng, được biết đến với việc dẫn đầu các dự án đột phá trong lĩnh vực xe điện, thám hiểm không gian và trí tuệ nhân tạo cũng như những mục tiêu đầy tham vọng của ông đối với nhân loại.\nIsaacson đã vẽ nên một bức tranh sống động về Musk như một \"đứa trẻ\" được hình thành từ những vết sẹo cả về thể xác lẫn tinh thần trong quá khứ, bao gồm cả việc bị bắt nạt khi còn nhỏ. Tính cách của Musk được đặc trưng bởi tính khí thất thường, khả năng chấp nhận rủi ro cao và quyết tâm kiên cường theo đuổi những sứ mệnh lớn lao.\nCuốn tiểu sử này cung cấp cái nhìn hậu trường về các hoạt động kinh doanh của Musk, bao gồm SpaceX, Tesla và việc anh tiếp quản Twitter, làm sáng tỏ phong cách lãnh đạo, nỗi ám ảnh về chi tiết và việc không ngừng theo đuổi các mục tiêu của mình.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704272042/elonmusk_2_2.jpg.jpg','vietnamese',756,7000,'Tiểu Sử Elon Musk',11),(17,0,27,'2024-01-03 16:02:43','Cuốn sách Tiếng Anh 7 Tập 1 - Sách Bài Tập giúp học sinh cũng cố khả năng phát âm, cách nhận biết các âm phát âm khác nhau và các âm khác nhau .\nNgoài ra cuốn sách còn cũng cố những từ ngữ và cấu trúc ngữ pháp và mở rộng vôn từ vựng thông qua các dạng bài tập khác nhau. Các khả năng nghe, đọc, viết , nói được đặc biệt chú ý trong cuốn sách này.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704272540/image_195509_1_43111_1.jpg.jpg','vietnamese',70,1000,'Tiếng Anh 7 - Tập 1',12),(18,0,72,'2024-01-03 16:08:19','Là tài liệu tham khảo cần thiết cho những học sinh muốn tìm hiểu kĩ về môn khoa học thú vị này. Đây là tài liệu dùng để ôn tập, chuẩn bị cho các kì thi học sinh giỏi, tuyển sinh vào các trường chuyên.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704272885/8935083581509.jpg.jpg','vietnamese',221,1000,'500 Bài Tập Vật Lí Trung Học Cơ Sở',13),(19,0,12,'2024-01-03 16:12:58','Destination B1 - Grammar And Vocabulary with Answer Key\nBộ sách cung cấp từ vựng và ngữ pháp tiếng Anh cần thiết nhất dành cho người học đang có ý định thi các kỳ thi ở Level B1, B2, C1, C2 theo Khung tham chiếu châu Âu và mong muốn cải thiện năng lực tiếng Anh của bản thân.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704273018/b1-1_1_5.jpg.jpg','english',248,5000,'Destination B1 - Grammar And Vocabulary with Answer Key',7),(20,8,12,'2024-01-03 16:17:55','Chiến Tranh Tiền Tệ - Phần 1 - Ai Thực Sự Là Người Giàu Nhất Thế Giới? (Tái bản 2022)\nGiá sản phẩm trên Tiki đã bao gồm thuế theo luật hiện hành. Bên cạnh đó, tuỳ vào loại sản phẩm, hình thức và địa chỉ giao hàng mà có thể phát sinh thêm chi phí khác như phí vận chuyển, phụ phí hàng cồng kềnh, thuế nhập khẩu (đối với đơn hàng giao từ nước ngoài có giá trị trên 1 triệu đồng).....',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1704273393/bia-truoc-chien-tranh-tien-te-phan-1-1.jpg.jpg','vietnamese',532,3000,'Chiến Tranh Tiền Tệ - Phần 1 - Ai Thực Sự Là Người Giàu Nhất Thế Giới? (Tái bản 2022)',10),(21,1,0,'2024-01-14 00:29:54','Truyện Nôm Lục Vân Tiên (ra đời khoảng năm 1854 lúc Nguyễn Đình Chiểu 32 tuổi , bản Nôm khắc in sớm nhất hiện ghi nhận được là do Quảng Thạnh Nam phát thụ, Duy Minh Thị đính chính, Tôn Thọ Tường trông nom, in ở Quảng Đông 1865), ',_binary '\0','https://res.cloudinary.com/dboo9wwlk/image/upload/v1705716794/luc_van_tien_ccjs0d.jpg','vietnamese',352,1000,'Lục Vân Tiên Và Những Luận Đề Về Nguyễn Đình Chiểu',8);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_author`
--

DROP TABLE IF EXISTS `book_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_author` (
  `book_id` bigint NOT NULL,
  `author_id` bigint NOT NULL,
  KEY `FKbjqhp85wjv8vpr0beygh6jsgo` (`author_id`),
  KEY `FKhwgu59n9o80xv75plf9ggj7xn` (`book_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_author`
--

LOCK TABLES `book_author` WRITE;
/*!40000 ALTER TABLE `book_author` DISABLE KEYS */;
INSERT INTO `book_author` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(13,14),(14,15),(15,16),(15,17),(16,18),(17,19),(18,20),(19,19),(20,21),(21,22);
/*!40000 ALTER TABLE `book_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_case`
--

DROP TABLE IF EXISTS `book_case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_case` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `no` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_case`
--

LOCK TABLES `book_case` WRITE;
/*!40000 ALTER TABLE `book_case` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_case` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_case_line`
--

DROP TABLE IF EXISTS `book_case_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_case_line` (
  `bookcase_id` bigint NOT NULL,
  `book_id` bigint NOT NULL,
  `line_id` bigint NOT NULL,
  PRIMARY KEY (`bookcase_id`,`book_id`,`line_id`),
  KEY `FK652cde0s8769o2alijd6qs94q` (`book_id`),
  KEY `FKjei334qbmgi0w0ovufouxaqpg` (`line_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_case_line`
--

LOCK TABLES `book_case_line` WRITE;
/*!40000 ALTER TABLE `book_case_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_case_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_department`
--

DROP TABLE IF EXISTS `book_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_department` (
  `book_id` bigint NOT NULL,
  `department_id` bigint NOT NULL,
  KEY `FKh0d1ecbd79lb6buxsn5j53b2d` (`department_id`),
  KEY `FKqpognoqfyn3xl4elviomqj2ao` (`book_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_department`
--

LOCK TABLES `book_department` WRITE;
/*!40000 ALTER TABLE `book_department` DISABLE KEYS */;
INSERT INTO `book_department` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,2),(7,2),(8,2),(9,3),(9,4),(9,5),(10,4),(11,3),(12,3),(12,4),(13,6),(13,10),(14,3),(14,4),(14,5),(15,8),(15,9),(16,11),(17,14),(18,15),(19,15),(20,8),(20,9),(21,2);
/*!40000 ALTER TABLE `book_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discounte` double NOT NULL,
  `is_ordered` bit(1) NOT NULL,
  `total_discounted_price` double NOT NULL,
  `total_item` int DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl70asp4l4w0jmbm1tqyofho4o` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,0,_binary '',20000,4,20000,3),(2,0,_binary '',8000,2,8000,3),(3,0,_binary '',3000,1,3000,3),(4,0,_binary '',48000,16,48000,3),(5,0,_binary '',17000,3,17000,3),(6,0,_binary '',42000,3,42000,3),(7,0,_binary '',42000,2,42000,3),(8,0,_binary '',8000,2,8000,3),(9,0,_binary '',6000,2,6000,4),(10,0,_binary '\0',6000,3,6000,2),(11,0,_binary '',12000,2,12000,3),(12,0,_binary '',15000,2,15000,11),(13,0,_binary '',11000,2,11000,11),(14,0,_binary '',10000,2,10000,3),(15,0,_binary '',4000,2,4000,3),(16,0,_binary '',12000,2,12000,3),(17,0,_binary '',8000,2,8000,4),(18,0,_binary '',16000,2,16000,4),(19,0,_binary '',5000,2,5000,4),(20,0,_binary '',6000,2,6000,4),(21,0,_binary '',64000,6,64000,4),(22,0,_binary '',8000,2,8000,3),(23,0,_binary '',8000,2,8000,3),(24,0,_binary '',8000,2,8000,3),(25,0,_binary '',40000,2,40000,3),(26,0,_binary '',11000,2,11000,4);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `checkout_date` datetime DEFAULT NULL,
  `discounted_price` double NOT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `return_date` datetime DEFAULT NULL,
  `book_id` bigint DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `voucher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKis5hg85qbs5d91etr4mvd4tx6` (`book_id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  KEY `FKjnaj4sjyqjkr4ivemf9gb25w` (`user_id`),
  KEY `FKbimhqirch02p3mwqsi6rtcu3w` (`voucher_id`)
) ENGINE=MyISAM AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (2,'2024-01-10 00:00:00',12000,12000,2,'2024-01-12 00:00:00',15,1,3,NULL),(3,'2024-01-11 00:00:00',2000,2000,1,'2024-01-13 00:00:00',3,1,3,NULL),(4,'2024-01-11 00:00:00',6000,6000,1,'2024-01-13 00:00:00',12,1,3,NULL),(5,'2024-01-13 00:00:00',8000,8000,2,'2024-01-17 00:00:00',6,2,3,NULL),(6,'2024-01-12 00:00:00',3000,3000,1,'2024-01-15 00:00:00',17,3,3,NULL),(7,'2024-01-13 00:00:00',8000,8000,8,'2024-01-13 00:00:00',18,4,3,NULL),(8,'2024-01-13 00:00:00',40000,40000,8,'2024-01-13 00:00:00',13,4,3,NULL),(9,'2024-01-13 00:00:00',10000,10000,1,'2024-01-15 00:00:00',13,5,3,NULL),(10,'2024-01-13 00:00:00',6000,6000,1,'2024-01-16 00:00:00',2,5,3,NULL),(11,'2024-01-13 00:00:00',1000,1000,1,'2024-01-14 00:00:00',4,5,3,NULL),(12,'2024-01-13 00:00:00',42000,42000,3,'2024-01-20 00:00:00',9,6,3,1),(13,'2024-01-13 00:00:00',24000,24000,1,'2024-01-19 00:00:00',10,7,3,1),(14,'2024-01-13 00:00:00',18000,18000,1,'2024-01-19 00:00:00',7,7,3,1),(30,'2024-01-15 00:00:00',6000,6000,1,'2024-01-17 00:00:00',15,11,3,1),(31,'2024-01-15 00:00:00',6000,6000,1,'2024-01-17 00:00:00',20,11,3,1),(26,'2024-01-15 00:00:00',4000,4000,2,'2024-01-17 00:00:00',3,10,2,NULL),(27,'2024-01-15 00:00:00',2000,2000,1,'2024-01-17 00:00:00',21,10,2,NULL),(28,'2024-01-15 00:00:00',6000,6000,1,'2024-01-17 00:00:00',20,8,3,1),(29,'2024-01-15 00:00:00',2000,2000,1,'2024-01-17 00:00:00',5,8,3,1),(32,'2024-01-15 00:00:00',6000,6000,1,'2024-01-17 00:00:00',20,12,11,NULL),(33,'2024-01-15 00:00:00',9000,9000,1,'2024-01-18 00:00:00',15,12,11,NULL),(34,'2024-01-15 00:00:00',1000,1000,1,'2024-01-16 00:00:00',18,13,11,NULL),(35,'2024-01-15 00:00:00',10000,10000,1,'2024-01-17 00:00:00',19,13,11,NULL),(36,'2024-01-15 00:00:00',4000,4000,1,'2024-01-16 00:00:00',11,14,3,1),(37,'2024-01-15 00:00:00',6000,6000,1,'2024-01-18 00:00:00',9,14,3,1),(38,'2024-01-15 00:00:00',2000,2000,1,'2024-01-16 00:00:00',2,15,3,1),(39,'2024-01-15 00:00:00',2000,2000,1,'2024-01-17 00:00:00',4,15,3,1),(43,'2024-01-15 00:00:00',4000,4000,1,'2024-01-17 00:00:00',9,9,4,2),(42,'2024-01-15 00:00:00',2000,2000,1,'2024-01-17 00:00:00',5,9,4,2),(44,'2024-01-16 00:00:00',2000,2000,1,'2024-01-18 00:00:00',4,17,4,2),(45,'2024-01-16 00:00:00',6000,6000,1,'2024-01-19 00:00:00',14,17,4,2),(46,'2024-01-16 00:00:00',8000,8000,1,'2024-01-18 00:00:00',8,18,4,2),(47,'2024-01-16 00:00:00',8000,8000,1,'2024-01-18 00:00:00',10,18,4,2),(50,'2024-01-16 00:00:00',3000,3000,1,'2024-01-19 00:00:00',5,19,4,2),(51,'2024-01-16 00:00:00',2000,2000,1,'2024-01-18 00:00:00',3,19,4,2),(52,'2024-01-16 00:00:00',3000,3000,1,'2024-01-19 00:00:00',5,20,4,2),(53,'2024-01-16 00:00:00',3000,3000,1,'2024-01-19 00:00:00',4,20,4,2),(54,'2024-01-16 00:00:00',4000,4000,1,'2024-01-20 00:00:00',3,21,4,2),(55,'2024-01-16 00:00:00',12000,12000,1,'2024-01-20 00:00:00',7,21,4,2),(56,'2024-01-16 00:00:00',3000,3000,1,'2024-01-19 00:00:00',3,16,3,1),(57,'2024-01-16 00:00:00',9000,9000,1,'2024-01-19 00:00:00',7,16,3,1),(58,'2024-01-16 00:00:00',4000,4000,1,'2024-01-20 00:00:00',3,22,3,1),(59,'2024-01-16 00:00:00',4000,4000,1,'2024-01-20 00:00:00',4,22,3,1),(60,'2024-01-16 00:00:00',4000,4000,1,'2024-01-20 00:00:00',3,23,3,1),(61,'2024-01-16 00:00:00',4000,4000,1,'2024-01-20 00:00:00',4,23,3,1),(62,'2024-01-16 00:00:00',2000,2000,1,'2024-01-18 00:00:00',3,24,3,1),(63,'2024-01-16 00:00:00',6000,6000,1,'2024-01-18 00:00:00',7,24,3,1),(64,'2024-01-18 00:00:00',28000,28000,1,'2024-01-22 00:00:00',16,25,3,1),(65,'2024-01-18 00:00:00',12000,12000,1,'2024-01-21 00:00:00',8,25,3,1),(66,'2024-01-19 00:00:00',24000,24000,2,'2024-01-22 00:00:00',10,21,4,2),(67,'2024-01-19 00:00:00',24000,24000,2,'2024-01-22 00:00:00',11,21,4,2),(70,'2024-01-21 00:00:00',3000,3000,1,'2024-01-24 00:00:00',21,26,4,2),(71,'2024-01-21 00:00:00',8000,8000,1,'2024-01-23 00:00:00',8,26,4,2);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departmment`
--

DROP TABLE IF EXISTS `departmment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departmment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departmment`
--

LOCK TABLES `departmment` WRITE;
/*!40000 ALTER TABLE `departmment` DISABLE KEYS */;
INSERT INTO `departmment` VALUES (1,'TIỂU THUYẾT 1'),(2,'TÔN GIÁO'),(3,'Kỹ Năng Sống'),(4,'Tâm Lý'),(5,'Rèn Luyện Nhân Cách'),(6,'Quản Trị - Lãnh Đạo'),(7,'Khởi Nghiệp - Làm Giàu'),(8,'Phân Tích Kinh Tế'),(9,'Tài Chính'),(10,'Nhân Sự - Việc Làm'),(11,'Câu Chuyện Cuộc Đời'),(12,'Lịch Sử'),(13,'Chính Trị'),(14,'Sách Giáo Khoa'),(15,'Sách Tham Khảo'),(16,'manga'),(17,'comic'),(18,'truyện ngắn-tản văn');
/*!40000 ALTER TABLE `departmment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_favorite` bit(1) NOT NULL,
  `book_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3c9qhul48t7s6uc34fl7avfp8` (`book_id`),
  KEY `FKh3f2dg11ibnht4fvnmx60jcif` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

LOCK TABLES `favorite` WRITE;
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
INSERT INTO `favorite` VALUES (1,_binary '',15,3),(2,_binary '\0',1,3),(3,_binary '\0',2,3),(4,_binary '\0',17,3),(5,_binary '',12,3),(6,_binary '',18,3),(7,_binary '',13,3),(8,_binary '\0',19,3),(9,_binary '',16,3),(10,_binary '\0',14,3),(11,_binary '',3,3),(12,_binary '',4,3);
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `line`
--

DROP TABLE IF EXISTS `line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `line` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `no` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line`
--

LOCK TABLES `line` WRITE;
/*!40000 ALTER TABLE `line` DISABLE KEYS */;
/*!40000 ALTER TABLE `line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `checkout_date` datetime DEFAULT NULL,
  `discounted_price` double NOT NULL,
  `is_extend` int NOT NULL,
  `is_return` bit(1) NOT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `return_date` datetime DEFAULT NULL,
  `send_deadline` bit(1) NOT NULL,
  `book_id` bigint DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `voucher_id` bigint DEFAULT NULL,
  `code_order` varchar(255) DEFAULT NULL,
  `is_payed` bit(1) NOT NULL,
  `old_return_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb033an1f8qmpbnfl0a6jb5njs` (`book_id`),
  KEY `FK87oi78dip7g6hd2qmor1pq6ol` (`employee_id`),
  KEY `FK74x5y5r0ye1atlufw3s4xlhu0` (`order_id`),
  KEY `FKt5mosdtftirppcdhv4wk963m` (`user_id`),
  KEY `FKjjj5amd7mdc395e51aoatiunx` (`voucher_id`)
) ENGINE=MyISAM AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,'2024-01-10 00:00:00',12000,3,_binary '',24000,2,'2024-01-23 00:00:00',_binary '',15,2,1,3,NULL,'JmDoAaovKtzI',_binary '\0','2024-01-12 00:00:00'),(2,'2024-01-11 00:00:00',2000,0,_binary '',4000,1,'2024-01-13 00:00:00',_binary '',3,5,1,3,NULL,'JmDoAaovKtzI',_binary '','2024-01-13 00:00:00'),(3,'2024-01-11 00:00:00',6000,0,_binary '',12000,1,'2024-01-13 00:00:00',_binary '',12,5,1,3,NULL,'JmDoAaovKtzI',_binary '','2024-01-13 00:00:00'),(4,'2024-01-13 00:00:00',8000,0,_binary '',32000,2,'2024-01-17 00:00:00',_binary '',6,5,2,3,NULL,'3Q6l6jCa5ijN',_binary '','2024-01-17 00:00:00'),(5,'2024-01-12 00:00:00',3000,0,_binary '',9000,1,'2024-01-15 00:00:00',_binary '',17,5,3,3,NULL,'e8jQ2Y9IuXe3',_binary '','2024-01-15 00:00:00'),(6,'2024-01-13 00:00:00',8000,0,_binary '',0,8,'2024-01-13 00:00:00',_binary '',18,5,4,3,NULL,'PIjQCy4Bcb89',_binary '','2024-01-13 00:00:00'),(7,'2024-01-13 00:00:00',40000,0,_binary '',0,8,'2024-01-13 00:00:00',_binary '',13,5,4,3,NULL,'PIjQCy4Bcb89',_binary '','2024-01-13 00:00:00'),(8,'2024-01-13 00:00:00',10000,0,_binary '',20000,1,'2024-01-15 00:00:00',_binary '',13,5,5,3,NULL,'PXj04e0ZMMMX',_binary '','2024-01-15 00:00:00'),(9,'2024-01-13 00:00:00',6000,0,_binary '',18000,1,'2024-01-16 00:00:00',_binary '',2,5,5,3,NULL,'PXj04e0ZMMMX',_binary '','2024-01-16 00:00:00'),(10,'2024-01-13 00:00:00',1000,0,_binary '',1000,1,'2024-01-14 00:00:00',_binary '',4,5,5,3,NULL,'PXj04e0ZMMMX',_binary '','2024-01-14 00:00:00'),(11,'2024-01-13 00:00:00',42000,0,_binary '\0',294000,3,'2024-01-20 00:00:00',_binary '',9,2,6,3,1,'tUhiXSrWDdLZ',_binary '\0','2024-01-20 00:00:00'),(12,'2024-01-13 00:00:00',24000,0,_binary '',144000,1,'2024-01-19 00:00:00',_binary '',10,5,7,3,1,'9Ihtx21fVPHv',_binary '','2024-01-19 00:00:00'),(13,'2024-01-13 00:00:00',18000,0,_binary '',108000,1,'2024-01-19 00:00:00',_binary '',7,5,7,3,1,'9Ihtx21fVPHv',_binary '','2024-01-19 00:00:00'),(14,'2024-01-15 00:00:00',0,0,_binary '',4000,2,'2024-01-17 00:00:00',_binary '',3,5,8,10,NULL,'DcgQjMa8oR25',_binary '','2024-01-17 00:00:00'),(15,'2024-01-15 00:00:00',0,0,_binary '',2000,1,'2024-01-17 00:00:00',_binary '',21,5,8,10,NULL,'DcgQjMa8oR25',_binary '','2024-01-17 00:00:00'),(16,'2024-01-15 00:00:00',6000,0,_binary '\0',12000,1,'2024-01-17 00:00:00',_binary '',20,5,9,3,1,'CQIjEOi3PrUk',_binary '\0','2024-01-17 00:00:00'),(17,'2024-01-15 00:00:00',2000,0,_binary '',4000,1,'2024-01-17 00:00:00',_binary '',5,5,9,3,1,'CQIjEOi3PrUk',_binary '','2024-01-17 00:00:00'),(18,'2024-01-15 00:00:00',6000,0,_binary '',12000,1,'2024-01-17 00:00:00',_binary '',15,5,10,3,1,'IGq1KWbi2TVA',_binary '','2024-01-17 00:00:00'),(19,'2024-01-15 00:00:00',6000,0,_binary '',12000,1,'2024-01-17 00:00:00',_binary '',20,5,10,3,1,'IGq1KWbi2TVA',_binary '','2024-01-17 00:00:00'),(20,'2024-01-15 00:00:00',6000,0,_binary '\0',12000,1,'2024-01-17 00:00:00',_binary '',20,5,11,11,NULL,'uNUbCH304Wqq',_binary '\0','2024-01-17 00:00:00'),(21,'2024-01-15 00:00:00',9000,0,_binary '\0',27000,1,'2024-01-18 00:00:00',_binary '',15,5,11,11,NULL,'uNUbCH304Wqq',_binary '\0','2024-01-18 00:00:00'),(22,'2024-01-15 00:00:00',1000,0,_binary '',1000,1,'2024-01-16 00:00:00',_binary '',18,5,12,11,NULL,'2sYd07AD9R83',_binary '','2024-01-16 00:00:00'),(23,'2024-01-15 00:00:00',10000,0,_binary '',10000,1,'2024-01-17 00:00:00',_binary '',19,5,12,11,NULL,'2sYd07AD9R83',_binary '','2024-01-17 00:00:00'),(24,'2024-01-15 00:00:00',4000,0,_binary '',4000,1,'2024-01-16 00:00:00',_binary '',11,5,13,3,1,'PISu124bHHil',_binary '','2024-01-16 00:00:00'),(25,'2024-01-15 00:00:00',6000,0,_binary '\0',6000,1,'2024-01-18 00:00:00',_binary '',9,5,13,3,1,'PISu124bHHil',_binary '\0','2024-01-18 00:00:00'),(26,'2024-01-15 00:00:00',2000,0,_binary '\0',2000,1,'2024-01-16 00:00:00',_binary '',2,5,14,3,1,'EsL2f7lDGFat',_binary '\0','2024-01-16 00:00:00'),(27,'2024-01-15 00:00:00',2000,0,_binary '\0',2000,1,'2024-01-17 00:00:00',_binary '',4,5,14,3,1,'EsL2f7lDGFat',_binary '\0','2024-01-17 00:00:00'),(28,'2024-01-15 00:00:00',4000,0,_binary '',4000,1,'2024-01-17 00:00:00',_binary '',9,5,15,4,2,'tJCt93NdqWhb',_binary '','2024-01-17 00:00:00'),(29,'2024-01-15 00:00:00',2000,0,_binary '',2000,1,'2024-01-17 00:00:00',_binary '',5,5,15,4,2,'tJCt93NdqWhb',_binary '','2024-01-17 00:00:00'),(30,'2024-01-16 00:00:00',2000,0,_binary '',2000,1,'2024-01-18 00:00:00',_binary '',4,5,16,4,2,'UQ3SJhmV6p9C',_binary '','2024-01-18 00:00:00'),(31,'2024-01-16 00:00:00',6000,0,_binary '\0',6000,1,'2024-01-19 00:00:00',_binary '',14,5,16,4,2,'UQ3SJhmV6p9C',_binary '\0','2024-01-19 00:00:00'),(32,'2024-01-16 00:00:00',8000,0,_binary '\0',8000,1,'2024-01-18 00:00:00',_binary '\0',8,NULL,NULL,4,2,NULL,_binary '\0','2024-01-18 00:00:00'),(33,'2024-01-16 00:00:00',8000,0,_binary '\0',8000,1,'2024-01-18 00:00:00',_binary '',8,5,17,4,2,'rUcxhOrzLKKG',_binary '\0','2024-01-18 00:00:00'),(34,'2024-01-16 00:00:00',8000,0,_binary '\0',8000,1,'2024-01-18 00:00:00',_binary '',10,5,17,4,2,'rUcxhOrzLKKG',_binary '\0','2024-01-18 00:00:00'),(35,'2024-01-16 00:00:00',8000,0,_binary '\0',8000,1,'2024-01-18 00:00:00',_binary '\0',8,NULL,NULL,4,2,NULL,_binary '\0','2024-01-18 00:00:00'),(36,'2024-01-16 00:00:00',8000,0,_binary '\0',8000,1,'2024-01-18 00:00:00',_binary '\0',8,NULL,NULL,4,2,NULL,_binary '\0','2024-01-18 00:00:00'),(37,'2024-01-16 00:00:00',8000,0,_binary '\0',8000,1,'2024-01-18 00:00:00',_binary '\0',8,NULL,NULL,4,2,NULL,_binary '\0','2024-01-18 00:00:00'),(38,'2024-01-16 00:00:00',3000,0,_binary '\0',3000,1,'2024-01-19 00:00:00',_binary '\0',5,NULL,NULL,4,2,NULL,_binary '\0','2024-01-19 00:00:00'),(39,'2024-01-16 00:00:00',3000,0,_binary '\0',3000,1,'2024-01-19 00:00:00',_binary '\0',5,NULL,NULL,4,2,NULL,_binary '\0','2024-01-19 00:00:00'),(40,'2024-01-16 00:00:00',3000,0,_binary '\0',3000,1,'2024-01-19 00:00:00',_binary '\0',5,NULL,NULL,4,2,NULL,_binary '\0','2024-01-19 00:00:00'),(41,'2024-01-16 00:00:00',3000,0,_binary '\0',3000,1,'2024-01-19 00:00:00',_binary '',5,5,18,4,2,'CeNjkB25pE3q',_binary '\0','2024-01-19 00:00:00'),(42,'2024-01-16 00:00:00',2000,0,_binary '\0',2000,1,'2024-01-18 00:00:00',_binary '',3,5,18,4,2,'CeNjkB25pE3q',_binary '\0','2024-01-18 00:00:00'),(43,'2024-01-16 00:00:00',1000,0,_binary '\0',1000,1,'2024-01-12 00:00:00',_binary '\0',5,NULL,NULL,4,2,NULL,_binary '\0','2024-01-12 00:00:00'),(44,'2024-01-16 00:00:00',3000,0,_binary '\0',3000,1,'2024-01-19 00:00:00',_binary '\0',4,NULL,NULL,4,2,NULL,_binary '\0','2024-01-19 00:00:00'),(45,'2024-01-16 00:00:00',1000,0,_binary '\0',1000,1,'2024-01-12 00:00:00',_binary '\0',5,NULL,NULL,4,2,NULL,_binary '\0','2024-01-12 00:00:00'),(46,'2024-01-16 00:00:00',3000,0,_binary '\0',3000,1,'2024-01-19 00:00:00',_binary '\0',4,NULL,NULL,4,2,NULL,_binary '\0','2024-01-19 00:00:00'),(47,'2024-01-16 00:00:00',3000,0,_binary '\0',3000,1,'2024-01-19 00:00:00',_binary '',5,5,19,4,2,'ta9F4OXDpz1c',_binary '\0','2024-01-19 00:00:00'),(48,'2024-01-16 00:00:00',3000,0,_binary '\0',3000,1,'2024-01-19 00:00:00',_binary '',4,5,19,4,2,'ta9F4OXDpz1c',_binary '\0','2024-01-19 00:00:00'),(49,'2024-01-16 00:00:00',3000,0,_binary '\0',0,1,'2024-01-19 00:00:00',_binary '',3,5,20,3,1,'PF47pdUIilcg',_binary '\0','2024-01-19 00:00:00'),(50,'2024-01-16 00:00:00',9000,0,_binary '\0',0,1,'2024-01-19 00:00:00',_binary '',7,5,20,3,1,'PF47pdUIilcg',_binary '\0','2024-01-19 00:00:00'),(51,'2024-01-16 00:00:00',4000,0,_binary '\0',4000,1,'2024-01-20 00:00:00',_binary '',3,5,21,3,1,'k1d8BNTyZY2W',_binary '\0','2024-01-20 00:00:00'),(52,'2024-01-16 00:00:00',4000,0,_binary '\0',4000,1,'2024-01-20 00:00:00',_binary '',4,5,21,3,1,'k1d8BNTyZY2W',_binary '\0','2024-01-20 00:00:00'),(53,'2024-01-16 00:00:00',4000,0,_binary '\0',4000,1,'2024-01-20 00:00:00',_binary '',3,5,22,3,1,'4oDkuWC4Gc0i',_binary '\0','2024-01-20 00:00:00'),(54,'2024-01-16 00:00:00',4000,0,_binary '\0',4000,1,'2024-01-20 00:00:00',_binary '',4,5,22,3,1,'4oDkuWC4Gc0i',_binary '\0','2024-01-20 00:00:00'),(55,'2024-01-16 00:00:00',2000,0,_binary '\0',1000,1,'2024-01-18 00:00:00',_binary '',3,5,23,3,1,'W7tFSE2XRdtT',_binary '','2024-01-18 00:00:00'),(56,'2024-01-16 00:00:00',6000,0,_binary '\0',3000,1,'2024-01-18 00:00:00',_binary '',7,5,23,3,1,'W7tFSE2XRdtT',_binary '','2024-01-18 00:00:00'),(57,'2024-01-17 00:00:00',0,0,_binary '\0',6000,1,'2024-01-23 00:00:00',_binary '',5,5,24,13,NULL,'SJKNpH1fKFOo',_binary '','2024-01-23 00:00:00'),(58,'2024-01-17 00:00:00',0,0,_binary '\0',18000,1,'2024-01-23 00:00:00',_binary '',7,5,24,13,NULL,'SJKNpH1fKFOo',_binary '','2024-01-23 00:00:00'),(59,'2024-01-17 00:00:00',0,0,_binary '\0',7000,1,'2024-01-24 00:00:00',_binary '',4,2,25,14,NULL,'Ezs3RYCeUCZK',_binary '','2024-01-24 00:00:00'),(60,'2024-01-17 00:00:00',0,0,_binary '\0',5000,1,'2024-01-22 00:00:00',_binary '',6,2,25,14,NULL,'Ezs3RYCeUCZK',_binary '','2024-01-22 00:00:00'),(61,'2024-01-17 00:00:00',0,0,_binary '\0',20000,2,'2024-01-22 00:00:00',_binary '',2,6,26,15,NULL,'X8xxOopYNu4j',_binary '','2024-01-22 00:00:00'),(62,'2024-01-17 00:00:00',0,0,_binary '\0',5000,1,'2024-01-22 00:00:00',_binary '',4,6,26,15,NULL,'X8xxOopYNu4j',_binary '','2024-01-22 00:00:00'),(63,'2024-01-18 00:00:00',28000,0,_binary '\0',14000,1,'2024-01-22 00:00:00',_binary '',16,7,27,3,1,'PvKDdqvZXA56',_binary '','2024-01-22 00:00:00'),(64,'2024-01-18 00:00:00',12000,0,_binary '\0',6000,1,'2024-01-21 00:00:00',_binary '',8,7,27,3,1,'PvKDdqvZXA56',_binary '','2024-01-21 00:00:00'),(65,'2024-01-16 00:00:00',4000,0,_binary '\0',2000,1,'2024-01-20 00:00:00',_binary '',3,5,28,4,2,'n4lU73cMeVQG',_binary '','2024-01-20 00:00:00'),(66,'2024-01-16 00:00:00',12000,0,_binary '\0',6000,1,'2024-01-20 00:00:00',_binary '',7,5,28,4,2,'n4lU73cMeVQG',_binary '','2024-01-20 00:00:00'),(67,'2024-01-19 00:00:00',24000,0,_binary '\0',12000,2,'2024-01-22 00:00:00',_binary '',10,5,28,4,2,'n4lU73cMeVQG',_binary '','2024-01-22 00:00:00'),(68,'2024-01-19 00:00:00',24000,0,_binary '\0',12000,2,'2024-01-22 00:00:00',_binary '',11,5,28,4,2,'n4lU73cMeVQG',_binary '','2024-01-22 00:00:00'),(69,'2024-01-21 00:00:00',3000,0,_binary '\0',2550,1,'2024-01-24 00:00:00',_binary '\0',21,NULL,NULL,4,2,NULL,_binary '\0','2024-01-24 00:00:00'),(70,'2024-01-21 00:00:00',3000,0,_binary '\0',2550,1,'2024-01-24 00:00:00',_binary '\0',21,5,29,4,2,'ESWXnZu02s1U',_binary '','2024-01-24 00:00:00'),(71,'2024-01-21 00:00:00',8000,0,_binary '\0',6800,1,'2024-01-23 00:00:00',_binary '\0',8,5,29,4,2,'ESWXnZu02s1U',_binary '','2024-01-23 00:00:00');
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderbook`
--

DROP TABLE IF EXISTS `orderbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderbook` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `discounte` double NOT NULL,
  `order_status` bit(1) NOT NULL,
  `return_order` bit(1) NOT NULL,
  `total_discounted_price` double NOT NULL,
  `total_item` int NOT NULL,
  `total_price` double NOT NULL,
  `employee_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnqu8nmwqy5ajj2qd4h2mi2o79` (`employee_id`),
  KEY `FKdexgf1yy7uty6icfgutpo5mjb` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderbook`
--

LOCK TABLES `orderbook` WRITE;
/*!40000 ALTER TABLE `orderbook` DISABLE KEYS */;
INSERT INTO `orderbook` VALUES (1,'JmDoAaovKtzI','2024-01-11 07:11:06',20000,_binary '',_binary '\0',0,14,40000,2,3),(2,'3Q6l6jCa5ijN','2024-01-12 23:42:33',8000,_binary '\0',_binary '',0,2,32000,2,3),(3,'e8jQ2Y9IuXe3','2024-01-12 23:55:18',3000,_binary '',_binary '\0',0,12,9000,2,3),(4,'PIjQCy4Bcb89','2024-01-13 20:44:05',48000,_binary '',_binary '\0',0,16,0,5,3),(5,'PXj04e0ZMMMX','2024-01-13 20:55:28',17000,_binary '',_binary '\0',0,3,39000,5,3),(6,'tUhiXSrWDdLZ','2024-01-13 21:26:59',42000,_binary '\0',_binary '',0,3,294000,2,3),(7,'9Ihtx21fVPHv','2024-01-13 21:40:24',42000,_binary '\0',_binary '',0,2,252000,5,3),(8,'DcgQjMa8oR25','2024-01-15 23:02:55',0,_binary '',_binary '\0',6000,3,6000,2,10),(9,'CQIjEOi3PrUk','2024-01-15 23:18:15',8000,_binary '\0',_binary '',0,2,16000,5,3),(10,'IGq1KWbi2TVA','2024-01-15 23:24:19',12000,_binary '\0',_binary '',0,2,24000,5,3),(11,'uNUbCH304Wqq','2024-01-15 23:29:32',15000,_binary '\0',_binary '',0,2,39000,5,11),(12,'2sYd07AD9R83','2024-01-15 23:33:25',11000,_binary '',_binary '\0',0,2,11000,5,11),(13,'PISu124bHHil','2024-01-15 23:35:12',10000,_binary '\0',_binary '',0,2,10000,5,3),(14,'EsL2f7lDGFat','2024-01-15 23:55:15',4000,_binary '\0',_binary '',0,2,4000,5,3),(15,'tJCt93NdqWhb','2024-01-15 23:59:54',6000,_binary '\0',_binary '',0,2,6000,5,4),(16,'UQ3SJhmV6p9C','2024-01-16 00:03:37',8000,_binary '\0',_binary '',0,2,8000,5,4),(17,'rUcxhOrzLKKG','2024-01-16 00:23:33',16000,_binary '\0',_binary '',0,2,16000,5,4),(18,'CeNjkB25pE3q','2024-01-16 00:46:12',5000,_binary '\0',_binary '',0,2,5000,5,4),(19,'ta9F4OXDpz1c','2024-01-16 00:55:45',6000,_binary '\0',_binary '',0,2,6000,5,4),(20,'PF47pdUIilcg','2024-01-16 00:59:37',12000,_binary '\0',_binary '',0,2,0,5,3),(21,'k1d8BNTyZY2W','2024-01-16 01:03:53',8000,_binary '\0',_binary '',0,2,8000,5,3),(22,'4oDkuWC4Gc0i','2024-01-16 01:08:44',8000,_binary '\0',_binary '',0,2,8000,5,3),(23,'W7tFSE2XRdtT','2024-01-16 10:08:07',8000,_binary '',_binary '\0',0,2,4000,5,3),(24,'SJKNpH1fKFOo','2024-01-17 21:15:05',0,_binary '',_binary '\0',24000,2,24000,5,13),(25,'Ezs3RYCeUCZK','2024-01-17 21:24:47',0,_binary '',_binary '\0',12000,2,12000,2,14),(26,'X8xxOopYNu4j','2024-01-17 21:55:02',0,_binary '',_binary '\0',25000,3,25000,6,15),(27,'PvKDdqvZXA56','2024-01-18 05:49:04',40000,_binary '',_binary '\0',0,2,20000,7,3),(28,'n4lU73cMeVQG','2024-01-19 21:08:35',64000,_binary '',_binary '\0',0,6,32000,5,4),(29,'ESWXnZu02s1U','2024-01-20 20:46:38',11000,_binary '',_binary '\0',0,2,9350,5,4);
/*!40000 ALTER TABLE `orderbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'NXB Hội Nhà Văn'),(2,'NXB Tri thức'),(3,'NXB Văn Học'),(4,'NXB Trẻ'),(5,'Nhà Xuất Bản Thế Giới'),(6,'NXB Tổng Hợp TPHCM'),(7,'NXB Hồng Đức'),(8,'Nhà Xuất Bản Phụ Nữ'),(9,'NXB Thanh Niên'),(10,'NXB Lao Động'),(11,'NXB Công Thương'),(12,'NXB Giáo Dục Việt Nam'),(13,'NXB Đại Học Quốc Gia TP.HCM'),(14,'NXB alpha'),(15,'NXB alpha3');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
  `book_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `stars` double DEFAULT NULL,
  PRIMARY KEY (`book_id`,`user_id`),
  KEY `FKpn05vbx6usw0c65tcyuce4dw5` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (15,3,'',2),(12,3,'',4),(9,3,'',4),(16,4,'',5),(7,4,'',5),(7,3,'',4),(4,3,'',4),(3,3,'',4),(1,4,'',4),(21,4,'',5);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_EMPLOYEE'),(3,'ROLE_USER'),(4,'ROLE_GUEST');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar_url` varchar(255) DEFAULT NULL,
  `active_code` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `gender` bit(1) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) NOT NULL,
  `user_status` bit(1) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `sent_email` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,NULL,'2024-01-03 13:33:17','2024-01-03 00:00:00','admin1234@gmail.com','first name',_binary '','last name','$2a$10$Ms2qgzW2A20Kk0xCWp..EuwP4YE.JFpqd.iSU2Ae.YgQpzBHN4YDq','0963135231',_binary '','admin1234',_binary ''),(2,NULL,NULL,'2024-01-03 13:33:40','2024-01-03 13:33:40','longcv210720@gmail.com','first name',_binary '','last name','$2a$10$5pDRd5Ss3WNkYYLUqywPkeGhxNsOpqSBgACFqa0Sq11qhhq921B06','0955667799',_binary '\0','employee1',_binary ''),(3,'http://res.cloudinary.com/dboo9wwlk/image/upload/v1704471464/earth.jpg.jpg',NULL,'2024-01-05 23:16:48','2000-07-19 00:00:00','nguyendinhlong21072000@gmail.com','happy',_binary '\0','long','$2a$10$.1Z4D9xYUd.gHPapGaS0hedfw44kfQgz7uNNY3UlD87KEAFe62X12','0923456789',_binary '','user123',_binary ''),(4,NULL,NULL,'2024-01-06 02:20:00','2024-01-06 02:20:00','longhappy2107@gmail.com','first name',_binary '','last name','$2a$10$rAD8HPkreWUnZCx.EgLOyO4PWeAojAlJwTJ5vb87P8jszRMH4LRhy','0926451821',_binary '','longkiha2172',_binary ''),(5,NULL,NULL,'2024-01-13 20:02:51','2003-07-22 00:00:00','employee2@gmail.com','first name',_binary '\0','last name','$2a$10$G/Og1ghLIHXxp0/RAS8m2ujNGZpdsCtc2vJ5Ikiajd4801ei9uQLG','0915667700',_binary '','employee2',_binary ''),(6,NULL,NULL,'2024-01-13 20:03:06','2024-01-13 20:03:06','employee3@gmail.com','first name',_binary '','last name','$2a$10$u8C3yei1cpAvLKZrqe9fiuoCM3TAcLTHIu0.DFS64dQj0vJ9qCDrG','0955667731',_binary '','employee3',_binary ''),(7,NULL,NULL,'2024-01-13 20:03:19','2002-01-09 00:00:00','employee4@gmail.com','Nguyễn Văn',_binary '','Chấn','$2a$10$vckkKyqFl0a/wZEojJw5UOnGgfR/5L58bxlZbTZsjK/AHhrokA6Rm','0912306333',_binary '','employee4',_binary ''),(8,NULL,NULL,'2024-01-13 20:04:10','2024-01-13 20:04:10','employee5@gmail.com','first name',_binary '','last name','$2a$10$EejV7PBLrXda9VRveVMtc.V4VwuJ1jfkN71jP50L8qLOfFmMP7FRa','0953651731',_binary '','employee5',_binary ''),(9,NULL,NULL,'2024-01-13 20:04:22','2024-01-13 20:04:22','employee6@gmail.com','first name',_binary '','last name','$2a$10$tqAf4C/Mx75wHaaW1L73Befc5Tjd64XBEDV/lSbVaOQ9knYaz3RKC','0923651731',_binary '\0','employee6',_binary ''),(10,NULL,NULL,'2024-01-15 23:02:55',NULL,'liemminh@gmail.com','hoang',_binary '\0',' van can','123@123','0912345763',_binary '\0','guest-10',_binary ''),(11,NULL,NULL,'2024-01-15 23:17:01','2024-01-15 23:17:01','naruto123@gmail.com','first name',_binary '','last name','$2a$10$PJ6Evl4nptiGpFAVMyW3mORYXFM1YHQJcXTvrv5C.zcJIkYz2MtYa','0914536237',_binary '','naruto123',_binary ''),(12,NULL,NULL,'2024-01-17 21:08:24',NULL,'longnghe14107@fpt.edu.vn','Nguyễn Gia',_binary '\0','Long','123@123','0912432667',_binary '\0','guest-12',_binary ''),(13,NULL,NULL,'2024-01-17 21:15:05',NULL,'happyta23w@gmail.com','nguyen',_binary '\0','gia','123@123','0921345451',_binary '\0','guest-13',_binary ''),(14,NULL,NULL,'2024-01-17 21:24:47',NULL,'asdjhadhsajz123@gmail.com','Nguyễn Tuasn',_binary '\0','Long','123@123','0912432667',_binary '\0','guest-14',_binary ''),(15,NULL,NULL,'2024-01-17 21:55:02',NULL,'sadsa123@gmail.com','dfgsdf',_binary '\0','33dfdfs','123@123','0912432667',_binary '\0','guest-15',_binary '');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `role_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,2),(3,3),(3,4),(2,5),(2,6),(2,7),(2,8),(2,9),(4,10),(3,11),(4,12),(4,13),(4,14),(4,15);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `due_day` int DEFAULT NULL,
  `percent` int DEFAULT NULL,
  `start_day` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfi7shrbmkuxg9pkec3vwc105r` (`cart_id`),
  KEY `FK5a2jebis31mofheltuonn88ql` (`employee_id`),
  KEY `FK4h57plnf4easro9xialxph4yy` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,'fYLCS4','voucher tạo bởi order của user',10,50,'2024-01-18',1,NULL,NULL,3),(2,'3v6HbL','voucher tạo bởi order của user',10,15,'2024-01-20',0,NULL,NULL,4);
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-20 20:56:45
