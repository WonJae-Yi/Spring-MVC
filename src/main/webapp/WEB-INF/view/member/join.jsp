<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<main>
	<h2 class="main title">회원가입 폼</h2>

	<div class="breadcrumb" style="margin-top: -20px;">
		<h3 class="hidden">경로</h3>
		<img src="../images/member/step2.png" alt="회원가입 1단계" />
	</div>


	<form id="form1" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend class="hidden">회원정보</legend>
			<table class="table margin-top first">
				<tbody>
					<tr>
						<th><label>아이디</label></th>
						<td colspan="3" class="text-align-left indent"><input
							id="id-text" type="text" name="id" class="width-half"
							required="required" value="" placeholder="영문과 숫자 6~20자 이내"
							pattern="^\w{6,20}$" /> <input class="btn-text btn-default"
							type="button" id="id-check-button" value="중복확인" /></td>
					</tr>
					<tr>
						<th><label>비밀번호</label></th>
						<td colspan="3" class="text-align-left indent">
							<input type="password" name="pwd" class="" required	placeholder="비밀번호 입력" />
						</td>
					</tr>
					<tr>
						<th><label>비밀번호 확인</label></th>
						<td colspan="3" class="text-align-left indent">
							<input class="" name="pwd2" type="password" required />
						</td>
					</tr>
					<tr>
						<th><label>이름</label></th>
						<td colspan="3" class="text-align-left indent">
							<input class="width-half" name="name" type="text" value=""	required="required" />
						</td>
					</tr>
					<!-- <tr>
                                    <th><label>필명</label></th>
                                    <td colspan="3" class="text-align-left indent"><input class="width-half" name="nicname" type="text" /></td>
                                </tr> -->
					<tr>
						<th><label>성별</label></th>
						<td colspan="3" class="text-align-left indent">
							<select	class="width-half" name="gender" required>
								<option value="">선택</option>
								<option value="남성">남성</option>
								<option value="여성">여성</option>
							</select>
						</td>
					</tr>
					<tr>
						<th><label>생년월일</label></th>
						<td colspan="3" class="text-align-left indent">
							<input name="birthday" type="date" class="width-half" required	placeholder="예) 2000-02-17" value="" /> <!-- <input name="y" type="text" class="width-small margin-hor" style="margin-left:0px;" />년
                            <input name="m" type="text" class="width-small margin-hor" />월
                            <input name="d" type="text" class="width-small margin-hor" />일 -->
							<input type="radio" name="isLunar" value="0" class="vertical-middle margin-hor" checked />양력 
							<input type="radio" name="isLunar" value="1" class="vertical-middle margin-hor" />음력
						</td>
					</tr>
					<tr>
						<th><label>핸드폰번호</label></th>
						<td colspan="3" class="text-align-left indent">
							<input	name="phone" type="text" class="width-half"	pattern="^01[016789]-\d{3,4}-\d{4}$" placeholder="예) 010-1111-2222" required value="" />
						</td>
					</tr>
					<tr>
						<th><label>이메일</label></th>
						<td colspan="3" class="text-align-left indent">
							<input name="email" type="email" class="width-half" required placeholder="예) user@newlecture.com" value="" />
						</td>
					</tr>

					<tr>
						<td colspan="4"><input type="hidden" name="" value="" /> 
							<input id="submit-Button" type="submit" name="btn" value="확인" style="height: 30px; margin: 20px;" class="btn-text btn-default" />
						</td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</form>
</main>