<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fetch Employee Data</title>
</head>
<body>
<h1>Employee Information</h1>
<button id="fetchButton">Fetch Employee Data</button>
<pre id="output"></pre>

<script>
    document.getElementById("fetchButton").addEventListener("click", async () => {
        try {
            // Gửi request đến servlet
            const response = await fetch("http://localhost:8080/your-project-name/employee");
            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);

            // Đọc dữ liệu JSON từ response
            const data = await response.json();

            // Xuất dữ liệu ra console và giao diện
            console.log(data);
            document.getElementById("output").textContent = JSON.stringify(data, null, 2);
        } catch (error) {
            console.error("Error fetching data:", error);
        }
    });
</script>
</body>
</html>
