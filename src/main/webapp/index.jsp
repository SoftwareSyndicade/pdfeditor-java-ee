<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>PDF Editor</title>
    <link href="styles/bootstrap.min.css" rel="stylesheet">
    <link href="styles/styles.css" rel="stylesheet">
    <link href="styles/index.css" rel="stylesheet">
</head>
<body>
    <div class="container-fluid h-100 p-3">
        <div class="row h-100 m-0">
            <div class="max-supported-width stage p-0">
            </div>
        </div>
    </div>
    <script src="scripts/jquery-3.6.0.js"></script>
    <script src="scripts/bootstrap.min.js"></script>
    <script src="scripts/WebViewer/lib/webviewer.min.js"></script>
    <script>
        var docURL = ""
        $(document).ready(function (){

            showUploadArea()

            $("html").on("dragover", function(e) {
                e.preventDefault();
                e.stopPropagation();
            });

            $("html").on("drop", function(e) { e.preventDefault(); e.stopPropagation(); });

            $('.upload-area').on('dragenter', function (e) {
                e.stopPropagation();
                e.preventDefault();
            });

            // Drag over
            $('.upload-area').on('dragover', function (e) {
                e.stopPropagation();
                e.preventDefault();
            });

            // Drop
            $('.upload-area').on('drop', function (e) {
                e.stopPropagation();
                e.preventDefault();
                parseFileType(e.originalEvent.dataTransfer.files[0])

            });

            $('input[type=file]').change(function (evt){
                parseFileType(this.files[0])
            })
        })

        function parseFileType(file){
            if(file.type === 'application/pdf'){
                let fd = new FormData();
                fd.append('file', file);
                uploadData(fd);
            }
            else{
                alert("Please upload PDF document.")
            }
        }

        function showUploadArea(){
            let stage = $('.stage')
            stage.empty()
            stage.append($('template#upload-area').html())
        }

        function uploadData(data){
            $.ajax({
                url:"pdf-uploader",
                method: 'POST',
                data: data,
                processData: false,
                contentType: false,
                success: function (res){
                    let uploadResponse = JSON.parse(res)
                    docURL = uploadResponse.pdf_URL
                    viewPDF()
                }
            })
        }

        function viewPDF() {

            let stage = $('.stage')
            stage.empty()
            stage.append($('template#pdf-viewer').html())



            WebViewer({
                path: 'scripts/WebViewer/lib', // path to the PDFTron 'lib' folder on your server
                initialDoc: docURL,
                // initialDoc: '/path/to/my/file.pdf', // You can also use documents on your server
            }, document.getElementById('viewer'))
                .then(instance => {
                    const docViewer = instance.Core.documentViewer;
                    const annotManager = instance.Core.annotationManager;
                    // call methods from instance, docViewer and annotManager as needed

                    // you can also access major namespaces from the instance as follows:
                    // const Tools = instance.Core.Tools;
                    // const Annotations = instance.Core.Annotations;

                    docViewer.addEventListener('documentLoaded', () => {
                        // call methods relating to the loaded document
                    });
                });
        }
    </script>

    <template id="upload-area">
        <div class="mt-3">
            <header class="text-center">
                <h3>Upload document here</h3>
            </header>
            <div class="upload-area">
                <div class="pdf-icon">
                    <img src="assets/pdf-icon.svg">
                </div>
                <div class="text-center p-2">
                    <h3>Drag and drop pdf here</h3>
                    <h4>OR</h4>
                </div>
                <div class="form-group">
                    <input type="file" class="form-control" accept="application/pdf">
                </div>
            </div>
            <div style="width: 800px;margin: auto" class="text-center padding2030">
                <h3>Recent uploads</h3>
                <div class="container-fluid mt-3">
                    <div class="row m-0 recent-pdf-stage">
                        <div style="width: 300px;margin: auto">
                            <img src="assets/empty-illustration.svg">
                            <h5>No recent uploads</h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </template>
    <template id="pdf-viewer">
        <div id='viewer' style='width: 100%;height: 100%'></div>
    </template>
    <template id="recent-pdf">
        <div class="col-4 p-2 recent-pdf">
            <div class="row m-0">
                <div class="col-3">
                    <img src="assets/pdf-icon.svg">
                </div>
                <div class="col-9 p-0 text-start ps-2" style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;position: relative">
                    <h4 id="pdf-name" style="position: absolute;top: 50%;transform: translateY(-50%)">PDF Name</h4>
                </div>
            </div>
        </div>
    </template>
</body>
</html>