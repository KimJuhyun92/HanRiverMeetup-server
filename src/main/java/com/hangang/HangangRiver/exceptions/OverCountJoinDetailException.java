package com.hangang.HangangRiver.exceptions;

public final class OverCountJoinDetailException extends Exception {
		@Override
		public String getMessage() {
			return "한 모임에는 20개 까지만 신청 가능합니다.";
		}
}
