$(function() {
    /*$("#sendCvBtn").click(function(){
        //validar ddl on click
        validarSelect('select#ProvSelect', '#selProv-error', 'una provincia');
        validarSelect('select#AreaIntSelect', '#selAreaInteres-error', 'un \u00e1rea de inter\u00e9s'); 
    });
   
    $('select').bind('change', function () {
        //validar ddl on change
        validarSelect('select#ProvSelect', '#selProv-error', 'una provincia');                
        validarSelect('select#AreaIntSelect', '#selAreaInteres-error', 'un \u00e1rea de inter\u00e9s');
    });*/
    
    //validar archivo a subir
    $('#fileToUpload').bind('change', function () {
        //recupero nombre y extension
        var extension = $('#fileToUpload').val().replace(/^.*\./, '');
        var nombreArchivo = $('#fileToUpload').val().replace(/.*[\/\\]/, '');
        
        //cuento los puntos para chekear que no sea doble extension.
        var count = 0;
        for (var i = 0; i< nombreArchivo.length; i++) {
            var caracter = nombreArchivo.charAt(i);
            if( caracter == ".") {
                count = count + 1;
             }
        }
        
        //valido que no sea doble extension.
        if(count >= 2){
            $('#archivo-error').html('<ul><li>El nombre del archivo es incorrecto.</li></ul>');
            $('#fileToUpload').val('');
        //valido que la extension sea la correcta.   
        }else if(extension !== 'pdf' && extension !== 'doc' && extension !== 'docx') {
            $('#archivo-error').html('<ul><li>Error, el archivo debe ser .doc, .docx o .pdf</li></ul>');
            $('#fileToUpload').val('');
        //valido que sea menor que un mb.    
        }else if (this.files[0].size > 1000000){
            $('#archivo-error').html('<ul><li>Error, el archivo debe tener un tama\u00f1o igual o menor a 1 MB.</li></ul>');
            $('#fileToUpload').val(''); 
        }else{
            $('#archivo-error').html('');
        }
    });
});

/*function validarSelect(select, selectErrorContainer, selectName){
    if ($(select).val() === '0')
            $(selectErrorContainer).html('<ul><li>Por favor seleccion\u00e1 ' + selectName + '</li></ul>');
        else
            $(selectErrorContainer).html('');
}*/