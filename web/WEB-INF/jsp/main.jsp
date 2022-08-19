<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <frameset rows="100,*,25" framespacing="0" border="0" frameborder="0">
        <frame src="page?methodName=toPage&pageName=top" name="TopMenu" scrolling="no" noresize/>
        <frameset cols="180,*" id="resize">
            <frame noresize name="menu" src="page?methodName=toPage&pageName=left" scrolling="yes"/>
            <frame noresize name="right" src="page?methodName=toPage&pageName=right" scrolling="yes"/>
        </frameset>
        <frame noresize name="status_bar" scrolling="no" src="page?methodName=toPage&pageName=bottom"/>
    </frameset>
</html>
