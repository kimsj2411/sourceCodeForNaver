 /*
  Name : myCartisNotEmpty.js
  Descriptin : Shopping Cart page on javascript
  Modification oInformation
  1. 2016.02.13. 김수진 최초생성
  2.

  since
  version 1.0
  see
  */

//전체선택 클릭
function CheckAll(){
	var allCheck = document.getElementById('select-all');
	var chk = document.getElementsByName("CID");

	if(allCheck.checked == true){
		calcTotal();
		for(var i=0; i<chk.length;i++){                                                                    
			chk[i].checked = true;     //모두 체크
			document.getElementById('select-all').checked=true;
		}
	}else{
		var totalPrice = document.getElementById('totalPrice');
		totalPrice.innerHTML=0;
		for(var i=0; i<chk.length;i++){                                                                    
		chk[i].checked = false;     //모두 해제
		document.getElementById('select-all').checked=false;
		}
	}
	checkCount();
}

//하단 전체선택 클릭
function CheckAllBottom(){
	var allCheck = document.getElementById('select-all2');
	var chk = document.getElementsByName("CID");

	if(allCheck.checked == true){
		calcTotal();
		for(var i=0; i<chk.length;i++){                                                                    
			chk[i].checked = true;     //모두 체크
			document.getElementById('select-all2').checked=true;
		}
	}else{
		var totalPrice = document.getElementById('totalPrice');
		totalPrice.innerHTML=0;
		for(var i=0; i<chk.length;i++){                                                                    
		chk[i].checked = false;     //모두 해제
		document.getElementById('select-all2').checked=false;
		}
	}
	checkCount();
}

//장바구니 내 상품 갯수 세기
function checkCount(){
	var chk=document.getElementsByName("CID");
	var chkCount = 0;
	
	for(var i =0; i<chk.length;i++){
		if(chk[i].checked==true){
			chkCount++;
		}			
	}
	var checkCount=document.getElementById('check-count');
	checkCount.innerHTML=chkCount;
}

//해당 상품 지우기
function delete_row(rows,product_idx,account_idx){
	 var current = window.event.srcElement;
    while ( (current = current.parentElement)  && current.id != "productContent");
         current.parentElement.removeChild(current);
         
    //recalculate total price  
    calcTotal(rows-1);
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '../php_db/wishList.php');
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
        	window.location.href = "./myCartisNotEmpty.html";
        }
    }
    var data = new Object();
    data.remove_product = product_idx;
    data.flag=3;
    data.account_idx=account_idx;
    console.log("data" + data);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(data)); 
    
    var cartTotalNum = document.getElementById('cart-countNumber').data;
    document.getElementById('cart-countNumber').innerHTML = cartTotalNum-1;
}

//장바구니 비우기
function delete_table(account_idx){
	
var r = confirm("선택한 상품을 삭제하시겠습니까?");
	if (r == true) {
		var tbody = document.getElementById("tbody");
		tbody.remove();
		var footer = document.getElementById("footer");
		footer.remove();
		var cartCount = document.getElementById("cart-countNumber");
		cartCount.innerHTML=0;

		//post product ihx which is removed
	    var xhr = new XMLHttpRequest();
	    xhr.open('POST', '../php_db/wishList.php');
	    xhr.onreadystatechange = function(){
	        if(xhr.readyState === 4 && xhr.status === 200){
	        	window.location.href = "./myCartisNotEmpty.html";
	        	showCartIsEmpty();
	        }
	    }
	    var data = new Object();
	    data.flag=4;
	    data.account_idx=account_idx;
	    xhr.setRequestHeader("Content-Type", "application/json");
	    xhr.send(JSON.stringify(data)); 
	} 
}

//수량 증가 버튼
function qty_plus(idx,rows,discountPrice){
	var input = document.getElementById('product_qty'+idx);
	var qty = input.value;
	qty++;
	
	if(qty <=0){
    	qty=idx;
    }

   input.value = qty;
    updateSubtotal(idx,rows, qty,discountPrice);
}	

function qty_minus(idx,rows,discountPrice){
	var input = document.getElementById('product_qty'+idx);
	var qty = input.value;

    qty--;
   if(qty <=0){
    	qty=idx;
    }
    input.value = qty;

 	updateSubtotal(idx,rows,qty,discountPrice);
}		

//수량 증감에 따른 해당상품 가격  업데이트
function updateSubtotal(i,rows, qty,discountPrice){

 	var onlineDiscount = document.getElementById('onlineDiscount'+i).innerText.slice(0,-1);
	
 	var discountSubTotal = discountPrice * qty;
 	
 	var subTotalcol = document.getElementById('subtotal'+i);
 	subTotalcol.innerHTML = discountSubTotal;
 	calcTotal(rows);

}

//전체 총상품 금액 계산
function calcTotal(rows){
	var result =0;

	for(i=0; i < rows; i++){
		if(document.getElementById('subtotal'+(i+1)) != null){
		var subTotalPrice = document.getElementById('subtotal'+(i+1)).innerText;
		}
				
		if(!isNaN(subTotalPrice)){
			result += parseInt(subTotalPrice);
		}

	}

	var totalPrice = document.getElementById('totalPrice');
	totalPrice.innerHTML=result;
}

//초기 해당 상품 계산
function calcSubtotal(rows){
	
	for(i=1; i <= rows; i++){
		var price = document.getElementById('onlineDiscount'+i).innerText.slice(0,-1);
		var qty = document.getElementById('product_qty'+i).value;
		var subTotal = price * qty;
		var subTotalPrice = document.getElementById('subtotal'+i);
		subTotalPrice.innerHTML = subTotal;
	}	

}

//체크박스 하나 선택
function selectChkbox(rows){
	var allCheck = document.getElementById('select-all');
	var chk = document.getElementsByName("CID");
	
	var result = 0;

	for(var i=0; i<rows;i++){   
		var subtotal = document.getElementById('subtotal'+(i+1));
		if(chk[i].checked == false){
			allCheck.checked=false;
		} else {
			result += parseInt(subtotal);
			allCheck.checkd==true;
		}
	}
	var totalPrice = document.getElementById('totalPrice');
	totalPrice.innerHTML=result;
	checkCount();
}

//총상품 곗수 세기
function countTotalProduct(){
	var table = document.getElementById('table');
	var tbody = table.getElementsByTagName("tbody").item(0);
	var rows = tbody.rows.length;
	
	var totalAmountTitle=document.getElementById('cart-count');
	totalAmountTitle.innerHTML=rows;
	var totalAmountBottom=document.getElementById('cart-count2');
	totalAmountBottom.innerHTML = rows;
}

//PC->Mobile시 지울 아이콘 선택
function changeToMobile(){
	  var cartIcon = document.getElementById("cartIcon")  
		cartIcon.setAttribute('class','text-center');  
	  	cartIcon.setAttribute('style','position:relative; left:20%;');
	  	
	  if( document.getElementById("thead") ) {
		var thead = document.getElementById("thead");
		document.getElementById("table").removeChild( document.getElementById("thead") );
		var tbody = document.getElementById("tbody");
		document.getElementById("table").removeChild( document.getElementById("tbody"));
	  }
}

function changeToPC(){
	 var cartIcon = document.getElementById("cartIcon")  
	 cartIcon.setAttribute('class','pull-left'); 
	 cartIcon.setAttribute('style','position:relative; left:0%;')
	 
	 var table = document.getElementById("table");
	 var thead = document.createElement("thead");
	 table.appendChild(thead);
	 
	 var cartCountNumber =document.getElementById("cart-countNumber");
}

//장바구니 비어있는지 보여주는 화면
function showCartIsEmpty(){
	var tableBlankDiv = document.createElement("div");
     tableBlankDiv.setAttribute('id','blank');
     tableBlankDiv.setAttribute('style','height:50px; width:100%');
     var blankH4 = document.createElement("h4");
     blankH4.setAttribute('class','text-center');
     var center = document.createElement("center");
     var strong = document.createElement("strong");
     strong.setAttribute('style','font-size: 15pt');
     strong.innerHTML="장바구니에 담은 상품이 없습니다.";   
     
     center.appendChild(strong);
	document.getElementById('footer').appendChild(tableBlankDiv);
 	document.getElementById('footer').appendChild(center);
}