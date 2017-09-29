var SUCCESS=0;
var ERROR=1;
$(function(){
	loadNotebooks();
	//绑定笔记本列表区域点击事件
	$('#notebook-list').on('click','.notebook',loadNotes);
	$('#note-list').on('click','.note',loadNote);
	$('#note-list').on('click','#add_note',showAddNoteDialog);
	$('#can').on('click','.close,.cancel',closeDialog);
	$('#can').on('click','.create-note',addNote);
	$('#save_note').on('click',updateNote);
})
function updateNote(){
	var url='note/update.do';
	var note=$(document).data('note');
	var data={noteId:note.id};
	var modified=false;
	var title=$('#input_note_title').val();
	if(title && title!=note.title){
		data.title=title;
		modified=true;
	}
	var body=um.getContent();
	if(body&&body!=note.body){
		data.body=body;
		modified=true;
	}
	if(modified){
		$.post(url,data,function(result){	
			if(result.state==0){
				//内存中的note改成新的数据
				note.title=title;
				note.body=body;
				var l=$('#note-list .check').parent();
				$('#note-list .check').remove();
				var li=noteTemplate.replace('[title]',title);
				var a=$(li).find('a');
				a.addClass('checked');
				l.prepend(a);
			}else{
				alert(result.message)
			}
		})
	}
}
function addNote(){
	var url='note/add.do';
	var notebookId=$(document).data('notebookId');
	var title=$('#input_note').val();
	var data={userId:getCookie('userId'),notebookId:notebookId,title:title};
	console.log(data);
	$.post(url,data,function(result){
		if(result.state==SUCCESS){
			var note=result.data;
			showNote(note);
			//找到显示笔记列表的ul对象
			var ul=$('#note-list ul');
			//创新新的笔记本的ul对象
			var li=noteTemplate.replace('[title]',note.title);
			li=$(li);
			
			ul.find('a').removeClass('checked');
			li.find('a').addClass('checked');
			//插入到笔记本表的第一位置
			
			ul.prepend(li);
			
			closeDialog();
		}else{
			alert(result.message);
		}
	});
}
function closeDialog(){
  $('.pacity_bg').hide();
  $('#can').empty();
}

function showAddNoteDialog(){
	var id=$(document).data('notebookId');
	if(id){
		$('#can').load('alert/alert_note.html',function(){
			$('#input_note').focus();
		});
		$('.opacty_bg').show();
		return;
	}	
}

function loadNote(){
	var li=$(this);
	var id=li.data('noteId');
	
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	var url='note/load.do';
	var data={noteId:id};
	
	$.getJSON(url,data,function(result){
		if(result.state==SUCCESS){
			var note=result.data;
			showNote(note);
		}else{
			alert(result.message);
		}
	});
}
function showNote(note){
	//显示笔记本标题
	$('#input_note_title').val(note.title);
	//显示笔记内容
	um.setContent(note.body);
	//绑定笔记信息，用于保存操作
	$(document).data('note',note);
}

function loadNotes(){
	var li=$(this);//当前被点击的对象
	//在被点击的笔记本li增加选定效果
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked')
	var url='note/list.do';
	var data={notebookId:li.data('notebookId')};
	
	$.getJSON(url,data,function(result){
		if(result.state==SUCCESS){
			var notes=result.data;
			showNotes(notes);
		}else{
			alert(result.message);
		}
	});
	$(document).data('notebookId',li.data('notebookId'));
}
function showNotes(notes){
	console.log(notes);
	var ul=$('#note-list ul');
	ul.empty();
	for(var i=0;i<notes.length;i++){
		var note=notes[i];
		var li=noteTemplate.replace('[title]',note.title);
		li=$(li);
		//将笔记ID绑定到li,用到点击笔记本显示笔记详细信息
		li.data('noteId',note.id);
		ul.append(li);
	}
}

function loadNotebooks(){	
	var url='notebook/list.do';
	var data={userId:getCookie('userId'),name:'demo'};
	$.getJSON(url,data,function(result){
		console.log(result);
		if(result.state==SUCCESS){
			var notebooks=result.data;
			//在showNotebooks方法中将全部的笔记本数据
			//notebooks显示到notebook—list区域
			showNotebooks(notebooks);
		}else{
			alert(result.message);
		}
	})
}
function showNotebooks(notebooks){
	//算法
	//找显示笔记本列表的区域的ul元素
	//元素，添加到ul元素
	var ul=$('#notebook-list ul');
	ul.empty();//清除ul中原有的内容
	for(var i=0;i<notebooks.length;i++){
		var notebook=notebooks[i];
		var li=notebookTemplate.replace('[name]',notebook.name);
		li=$(li);
		//将notebook.id绑定li
		li.data('notebookId',notebook.id);
		ul.append(li);
	}
}

var notebookTemplate= 
	'<li class="online notebook">'+
    '<a><i class="fa fa-book" title="online" '+
    'rel="tooltip-bottom"></i> [name]</a>'+
    '</li>';

var noteTemplate='<li class="online note">'+
'<a>'+
'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>[title]<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>' 
'<div class="note_menu" tabindex="-1">'+
'<dl>'+
	'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
	'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
	'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
'</dl>'+
'</div>'+
'</li>';


