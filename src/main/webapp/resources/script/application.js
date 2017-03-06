function disableSubmit(finalResult,submitButtonId) {
	if(finalResult) {
		document.getElementById(submitButtonId).disabled = true;
		return finalResult;
	}else {
		return finalResult;
	}
}

function batchDelete(action,checkboxName,form){
    if (!hasOneChecked(checkboxName)){
            alert('请选择要操作的对象!');
            return;
    }
    if (confirm('确定执行[删除]操作?')){
        form.action = action;
        form.submit();
    }
}

function hasOneChecked(name){
    var items = document.getElementsByName(name);
    if (items.length > 0) {
        for (var i = 0; i < items.length; i++){
            if (items[i].checked == true){
                return true;
            }
        }
    } else {
        if (items.checked == true) {
            return true;
        }
    }
    return false;
}

function setAllCheckboxState(name,state) {
	var elms = document.getElementsByName(name);
	for(var i = 0; i < elms.length; i++) {
		elms[i].checked = state;
	}
}

function getReferenceForm(elm) {
	while(elm && elm.tagName != 'BODY') {
		if(elm.tagName == 'FORM') return elm;
		elm = elm.parentNode;
	}
	return null;
}
/*function outExcel(formObj,url)
{
	var tempAction = formObj.action;
	
	formObj.action = url;
	formObj.submit();
	formObj.action  = tempAction;
}*/

function outExcel(url,e)
{
	var qForm = document.queryForm;
	var tempAction = qForm.action;
	
	var input = document.createElement("input"); 
    input.type = "hidden";
    input.name = "ts";
    input.value = (new Date()).getTime();
	qForm.appendChild(input);

	qForm.action = url;
	qForm.submit();
	stopDefault(e);
	qForm.action  = tempAction;
	
	qForm.removeChild(input);
}


function exportExcel(url,e)
{  
	var qForm = document.queryForm;
	var tempAction = qForm.action;
	var tempMethod =qForm.method;
	var input = document.createElement("input"); 
    input.type = "hidden";
    input.name = "ts";
    input.value = (new Date()).getTime();
	qForm.appendChild(input);

	qForm.action = url;
	qForm.method ="post";
	qForm.submit();
	stopDefault(e);
	qForm.action  = tempAction;
	qForm.method  = tempMethod;
	
	qForm.removeChild(input);
}
function stopDefault(e)
{
	   if(e==null) return false;
       //é»æ­¢é»è®¤æµè§å¨å¨ä½(W3C)
       if ( e && e.preventDefault )
           e.preventDefault();
       //IEä¸­é»æ­¢å½æ°å¨é»è®¤å¨ä½çæ¹å¼
       else
           window.event.returnValue = false;
       return false;
}