<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>PDF Reader</title>
    <link href="styles/bootstrap.min.css" rel="stylesheet">
    <link href="styles/styles.css" rel="stylesheet">
    <link href="styles/index.css" rel="stylesheet">
</head>
<body>
    <div class="container-fluid max-supported-width h-100 p-3">
        <div class="stage">
            <div class="text-center padding2030">
                <h2>Choose file to upload</h2>
                <div style="border:1px dashed;border-radius: 0.5rem;width: 50%;margin: auto" class="padding2030">
                    <img src="assets/pdf-icon.svg" style="width: 70px">
                    <div class="mt-3">
                        <h5 class="form-label">Drag and drop a pdf file</h5>
                        <h6>OR</h6>
                        <input class="form-control" type="file" id="formFile">
                    </div>
                </div>

            </div>
        </div>
    </div>

    <script src="scripts/jquery-3.6.0.js"></script>
    <script src="scripts/bootstrap.min.js"></script>
</body>
</html>