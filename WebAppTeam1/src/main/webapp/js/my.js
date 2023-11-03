$(function(){

		let maxid = 0;
		setInterval(function(){
			$.ajax(
				{url:"MessageController?id=" + maxid, dataType:"json"}
			).done(
				
				
				
				function(messages){
					let before = "";	
					// loop for messages
					for(m of messages) {
						
						let s = new Date(m.sendAt);
							
							
						let balloon="";
						
						if(before != (s.getMonth() + 1) + "/" + s.getDate()) {
							balloon += "<div class='text-center'>" + (s.getMonth() + 1) + "/" + s.getDate() + "</div>";
						}
						before = (s.getMonth() + 1) + "/" + s.getDate();
							
						if(userid == m.user.userid){
							balloon +="<div class='kaiwa line'>"+
					
						    "<div class='fukidasi right'>"+
						        m.message + "</div><div>&nbsp</div><div class='text-end'>" + s.getHours().toString() + ":" + s.getMinutes().toString().padStart(2, "0") +
						    "</div>";
							
						}else{
							balloon += "<div class='kaiwa line'>"+
						    "<div class='name'>"+
						        m.user.username+
						   " </div>"+
						    "<div class='fukidasi left'>"+
						        "<img class='icon' src='images/"+m.user.profilepicture+"' alt=''>"+m.message+
						    "</div><div>&nbsp</div><div>" + s.getHours() + ":" + s.getMinutes().toString().padStart(2, "0") + "</div>";
						}
							
						$("#msg").append(balloon);
						$("#msg").scrollTop($("#msg")[0].scrollHeight);
						maxid = m.messageid;
					}
				}
			);
		}, 2000);
	});

	function send() {
		$.ajax(
				{
					url:"MessageController", 
					type:"POST",
					data:{"userid" : userid, "message" : $("#txt").val()}
				}
			).done(function(){
				$("#txt").val("");
			});
	}
	