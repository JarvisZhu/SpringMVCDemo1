
/**
 * api for RESTful operation
 */
/**
 * use case: <a href="/user/12" onclick="doRestDelete(this,'confirm delete?');return false;">delete</a>
 */

//单个恢复
function doRestUpdate(anchor, confirmMsg) {
	if (confirmMsg && confirm(confirmMsg)) {
		var f = document.createElement("form");
		f.style.display = "none";
		anchor.parentNode.appendChild(f);
		f.method = "POST";
		f.action = anchor.href;
		var m = document.createElement("input");
		m.setAttribute("type", "hidden");
		m.setAttribute("name", "_method");
		m.setAttribute("value", "put");
		f.appendChild(m);
		f.submit();
	}
}

//单个删除
function doRestDelete(anchor, confirmMsg) {
	if (confirmMsg && confirm(confirmMsg)) {
		var f = document.createElement("form");
		f.style.display = "none";
		anchor.parentNode.appendChild(f);
		f.method = "POST";
		f.action = anchor.href;
		var m = document.createElement("input");
		m.setAttribute("type", "hidden");
		m.setAttribute("name", "_method");
		m.setAttribute("value", "delete");
		f.appendChild(m);
		f.submit();
	}
}
//单个停用
function doRestStop(anchor, confirmMsg) {
	if (confirmMsg && confirm(confirmMsg)) {
		var f = document.createElement("form");
		f.style.display = "none";
		anchor.parentNode.appendChild(f);
		f.method = "POST";
		f.action = anchor.href;
		var m = document.createElement("input");
		m.setAttribute("type", "hidden");
		m.setAttribute("name", "_method");
		m.setAttribute("value", "post");
		f.appendChild(m);
		f.submit();
	}
}
//单个审核
function doRestAudit(anchor, confirmMsg) {
	if (confirmMsg && confirm(confirmMsg)) {
		var f = document.createElement("form");
		f.style.display = "none";
		anchor.parentNode.appendChild(f);
		f.method = "POST";
		f.action = anchor.href;
		var m = document.createElement("input");
		m.setAttribute("type", "hidden");
		m.setAttribute("name", "_method");
		m.setAttribute("value", "post");
		f.appendChild(m);
		f.submit();
	}
}
//单个冻结(票券订单)
function doRestFraze(anchor, confirmMsg) {
	if (confirmMsg && confirm(confirmMsg)) {
		var f = document.createElement("form");
		f.style.display = "none";
		anchor.parentNode.appendChild(f);
		f.method = "POST";
		f.action = anchor.href;
		var m = document.createElement("input");
		m.setAttribute("type", "hidden");
		m.setAttribute("name", "_method");
		m.setAttribute("value", "post");
		f.appendChild(m);
		f.submit();
	}
}
//单个解冻(票券订单)
function doRestUNFraze(anchor, confirmMsg) {
	if (confirmMsg && confirm(confirmMsg)) {
		var f = document.createElement("form");
		f.style.display = "none";
		anchor.parentNode.appendChild(f);
		f.method = "POST";
		f.action = anchor.href;
		var m = document.createElement("input");
		m.setAttribute("type", "hidden");
		m.setAttribute("name", "_method");
		m.setAttribute("value", "post");
		f.appendChild(m);
		f.submit();
	}
}
//批量删除
function doRestBatchDelete(action, checkboxName, form) {
	if (!hasOneChecked(checkboxName)) {
		alert("\u8bf7\u9009\u62e9\u4f60\u8981\u5220\u9664\u7684\u5bf9\u8c61!");
		return;
	}
	if (confirm("\u4f60\u786e\u8ba4\u8981\u5220\u9664?")) {
		if (!form) {
			form = document.forms["queryForm"];
		}
		form.action = action;
		form.method = "POST";
		var m = document.createElement("input");
		m.setAttribute("type", "hidden");
		m.setAttribute("name", "_method");
		m.setAttribute("value", "delete");
		form.appendChild(m);
		form.submit();
	}
}

//批量审核
function doRestBatchAudit(action, checkboxName, form) {
	if (!hasOneChecked(checkboxName)) {
		alert("\u8bf7\u9009\u62e9\u60a8\u8981\u5ba1\u6838\u7684\u5ba2\u6237!");
		return;
	}
	if (confirm("\u60a8\u786e\u5b9a\u8981\u5ba1\u6838?")) {
		form.action = action + "/batchAudit";
		form.method = "POST";
		form.submit();
	}
}
//批量停用
function doRestBatchStop(action, checkboxName, form) {
	if (!hasOneChecked(checkboxName)) {
		alert("\u8bf7\u9009\u62e9\u60a8\u8981\u505c\u7528\u7684\u5ba2\u6237!");
		return;
	}
	var flag = false;
	$(".items:checked").each(function () {
		if (!flag) {
			var status = $.trim($(this).parent("td").parent("tr").children("td").eq(7).html());
			if (status.indexOf("\u5df2\u5ba1\u6838") < 0) {
				alert("\u53ea\u6709\u5ba1\u6838\u901a\u8fc7\u7684\u5ba2\u6237\u624d\u53ef\u4ee5\u505c\u7528\uff01\u8bf7\u91cd\u65b0\u9009\u62e9");
				flag = true;
				return;
			}
		}
	});
	if (flag) {
		return;
	}
	if (confirm("\u60a8\u786e\u5b9a\u8981\u505c\u7528?")) {
		form.action = action + "/batchStop";
		form.method = "POST";
		form.submit();
	}
}
function doBatchPromote(action, checkboxName, form) {
	if (!hasOneChecked(checkboxName)) {
		alert("\u8bf7\u9009\u62e9\u4f60\u8981\u63d0\u5347\u7684\u5bf9\u8c61!");
		return;
	}
	if (confirm("\u4f60\u786e\u8ba4\u8981\u63d0\u5347\u4e3a\u4f1a\u5458\u5417?")) {
		if (!form) {
			form = document.forms["queryForm"];
		}
		form.action = action;
		form.method = "POST";
		form.submit();
	}
}

//多个对象的批量审核
function doBatchAudit(action, checkboxName, form) {
	if (!hasOneChecked(checkboxName)) {
		alert("\u8bf7\u9009\u62e9\u60a8\u8981\u5ba1\u6838\u7684\u5bf9\u8c61!");
		return;
	}
	if (confirm("\u5ba1\u6838\u540e\u5ba2\u6237\u548c\u5f71\u9662\u5c06\u4f1a\u770b\u5230\u8fd9\u4e9b\u516c\u544a\uff0c\n\u8981\u52a0\u53d1\u77ed\u4fe1\u3001\u90ae\u4ef6\u7684\u5c06\u4f1a\u53d1\u9001\u77ed\u4fe1\u3001\u90ae\u4ef6\u7ed9\u5ba2\u6237\u548c\u5f71\u9662\u3002\n\n\u60a8\u786e\u5b9a\u8981\u5ba1\u6838?")) {
		form.action = action + "/batchAudit";
		form.method = "POST";
		form.submit();
	}
}

//多个对象的批量结算
function doBatchBalance(action, checkboxName, form) {
	if (!hasOneChecked(checkboxName)) {
		alert("\u8bf7\u9009\u62e9\u4f60\u8981\u7ed3\u7b97\u7684\u5bf9\u8c61!");
		return;
	}
	if (confirm("\u4f60\u786e\u8ba4\u8981\u7ed3\u7b97?")) {
		form.action = action + "/batchBalance";
		form.method = "POST";
		form.submit();
	}
}
function doresetSecret(action, checkboxName, form) {
	if (!hasOneChecked(checkboxName)) {
		alert("\u8bf7\u9009\u62e9\u4f60\u8981\u91cd\u7f6e\u5bc6\u94a5\u7684\u5546\u5bb6!");
		return;
	}
	var items = document.getElementsByName(checkboxName);
	var num = 0;
	if (items.length > 0) {
		for (var i = 0; i < items.length; i++) {
			if (items[i].checked == true) {
				num = num + 1;
			}
		}
	}
	if (num > 1) {
		alert("\u53ea\u80fd\u9009\u62e9\u4e00\u4e2a\u5546\u5bb6\u91cd\u7f6e\u5176\u5bc6\u94a5");
		return;
	}
	if (confirm("\u4f60\u786e\u8ba4\u8981\u91cd\u7f6e\u5bc6\u94a5?")) {
		if (!form) {
			form = document.forms["queryForm"];
		}
		form.action = action + "/reset";
		form.method = "POST";
		form.submit();
	}
}

