<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload File</title>
</head>
<body>
<h1>Upload File</h1>
<form id="uploadForm">
    <input type="file" id="fileInput" name="file" required>
    <button type="submit">Upload</button>
</form>

<pre id="output"></pre>

<script>
    document.getElementById("uploadForm").addEventListener("submit", async function(event) {
        event.preventDefault(); // Ngừng hành động mặc định của form

        const formData = new FormData();
        const fileInput = document.getElementById("fileInput");

        // Thêm file vào FormData
        formData.append("file", fileInput.files[0]);

        try {
            // Gửi yêu cầu upload file đến servlet
            const response = await fetch("http://localhost:8080/SOF3012_war/upload", {
                method: "POST",
                body: formData
            });

            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);

            // Đọc dữ liệu JSON từ phản hồi
            const data = await response.json();

            // Xuất dữ liệu JSON ra console và giao diện
            console.log(data);
            document.getElementById("output").textContent = JSON.stringify(data, null, 2);
        } catch (error) {
            console.error("Error uploading file:", error);
        }
    });
</script>
</body>
</html>
