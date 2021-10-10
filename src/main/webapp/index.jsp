<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>PDF Reader</title>
    <meta http-equiv="Content-Type" content="text/html" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />

    <link href="styles/bootstrap.min.css" rel="stylesheet">
    <link href="styles/styles.css" rel="stylesheet">
    <link href="styles/index.css" rel="stylesheet">
</head>
<body>
    <div class="container-fluid max-supported-width h-100 p-3">
        <div class="stage">
<%--            <div class="text-center padding2030">--%>
<%--                <h2>Choose file to upload</h2>--%>
<%--                <div style="border:1px dashed;border-radius: 0.5rem;max-width: 50%;margin: auto" class="padding2030 upload-area">--%>
<%--                    <img src="assets/pdf-icon.svg" style="width: 70px">--%>
<%--                    <div class="mt-3">--%>
<%--                        <h5 class="form-label">Drag and drop a pdf file</h5>--%>
<%--                        <h6>OR</h6>--%>
<%--                        <input class="form-control" type="file" id="formFile" accept="application/pdf">--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--            </div>--%>

<%--            <div id='viewer' style='width: 100%;height: 100%'></div>--%>
        </div>
    </div>

    <script src="scripts/jquery-3.6.0.js"></script>
    <script src="scripts/bootstrap.min.js"></script>
    <script src="scripts/WebViewer/lib/webviewer.min.js"></script>
    <script>

        $(document).ready(function (){
            let stage = $('.stage')
            stage.empty()
            stage.append($('template#upload-area').html())

            $("html").on("dragover", function(e) {
                e.preventDefault();
                e.stopPropagation();
            });

            $("html").on("drop", function(e) { e.preventDefault(); e.stopPropagation(); });

            $('.upload-area').on('dragenter', function (e) {
                e.stopPropagation();
                e.preventDefault();
                $('.stage').empty()
            });

            // Drag over
            $('.upload-area').on('dragover', function (e) {
                debugger
                e.stopPropagation();
                e.preventDefault();
                // $("h1").text("Drop");
                $('.stage').empty()
            });

            $('.upload-area').on('dragover', function (e) {
                e.stopPropagation();
                e.preventDefault();
                // $("h1").text("Drop");
                $('.stage').empty()
            });
        })
        // async function createPdf() {
        //     WebViewer({
        //         path: 'scripts/WebViewer/lib', // path to the PDFTron 'lib' folder on your server
        //         initialDoc: 'https://pdftron.s3.amazonaws.com/downloads/pl/webviewer-demo.pdf',
        //         // initialDoc: '/path/to/my/file.pdf', // You can also use documents on your server
        //     }, document.getElementById('viewer'))
        //         .then(instance => {
        //             const docViewer = instance.Core.documentViewer;
        //             const annotManager = instance.Core.annotationManager;
        //             // call methods from instance, docViewer and annotManager as needed
        //
        //             // you can also access major namespaces from the instance as follows:
        //             // const Tools = instance.Core.Tools;
        //             // const Annotations = instance.Core.Annotations;
        //
        //             docViewer.addEventListener('documentLoaded', () => {
        //                 // call methods relating to the loaded document
        //             });
        //         });
        // }





        $('input[type=file]').change(function (){

        })
    </script>

    <template id="upload-area">
        <div class="text-center padding2030">
            <h2>Choose file to upload</h2>
            <div class="padding2030 upload-area">
                <img src="assets/pdf-icon.svg" style="width: 70px">
                <div class="mt-3">
                    <h5 class="form-label">Drag and drop a pdf file</h5>
                    <h6>OR</h6>
                    <input class="form-control" type="file" id="formFile" accept="application/pdf">
                </div>
            </div>
        </div>
    </template>
</body>
</html>