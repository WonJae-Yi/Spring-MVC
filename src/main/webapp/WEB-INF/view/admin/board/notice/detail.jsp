<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    	
<!DOCTYPE html>
<main>
	<h2 class="main title">공지사항</h2>

	<div class="breadcrumb">
		<h3 class="hidden">breadlet</h3>
		<ul>
			<li>home</li>
			<li>고객센터</li>
			<li>공지사항</li>
		</ul>
	</div>

	<div class="margin-top first">
		<h3 class="hidden">공지사항 내용</h3>
		<table class="table">
			<tbody>
				<tr>
								<th>제목</th>
								<td class="text-align-left text-indent text-strong text-orange" colspan="3">${n.title}</td>
								<tr>
									<th>작성일</th>
									<td class="text-align-left text-indent" colspan="3"><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${n.regdate}"></fmt:formatDate></td>
								</tr>
								<tr>
									<th>작성자</th>
									<td>${n.writerid}</td>
									<th>조회수</th>
									<td><fmt:formatNumber pattern="#,##0" value="${n.hit}"></fmt:formatNumber></td>
								</tr>
								<tr>
									<th>첨부파일</th>
									<td colspan="3" class="text-align-left text-indent" >
									<c:forTokens var="fileName" items="${n.files}" delims="," varStatus="st">
									
										<c:set var="style" value="" />
										<c:if test="${fn:endsWith(fileName, '.zip') }">
											<c:set var="style" value="font-weight:bold; color:red" />
										
										</c:if>
										<a download href="/JSPBasic/upload/${fileName}" style="${style}">${fn:toUpperCase(fileName)}</a>
										<c:if test="${!st.last}">
										/
										</c:if>
									</c:forTokens>
									
									</td>
								</tr>
								<tr class="content">
									<td colspan="4">${n.content}</td>
								</tr>
			</tbody>
		</table>
	</div>

	<div class="margin-top text-align-center">
		<a class="btn-text btn-cancel" href="list">목록</a> <a
			class="btn-text btn-default" href="edit.html">수정</a> <a
			class="btn-text btn-default" href="del.html">삭제</a>
	</div>

	<div class="margin-top">
		<table class="table border-top-default">
			<tbody>
				<tr>
                   	<th>다음글</th>
                    <c:choose>
					<c:when  test="${!empty nextn.id}">
						<td colspan="3"  class="text-align-left text-indent">
							<a href="detail?id=${nextn.id}">${nextn.title}</a>
						</td>            
	    							</c:when>
					<c:otherwise>
						<td colspan="3"  class="text-align-left text-indent">다음글이 없습니다.</td>
	    							</c:otherwise>
					</c:choose>
                     </tr>
                     <tr>
                         <th>이전글</th>
                         <c:choose>
					<c:when  test="${!empty prevn.id}">
						<td colspan="3"  class="text-align-left text-indent">
							<a href="detail?id=${prevn.id}">${prevn.title}</a>
						</td>            
	    							</c:when>
					<c:otherwise>
						<td colspan="3"  class="text-align-left text-indent">이전글이 없습니다.</td>
	    							</c:otherwise>
				</c:choose>
	                       </tr>
			</tbody>
		</table>
	</div>
</main>