package com.carblre.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carblre.dto.TossHistoryDTO;
import com.carblre.dto.TossResponseDTO;
import com.carblre.repository.interfaces.PaymentHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Autowierd 없이 생성자 주입가능

public class PaymentService {

	private final PaymentHistoryRepository historyRepository;

	/**
	 * 결제승인
	 * 
	 * @param responseDTO
	 * @param principalId
	 * @return
	 */
	@Transactional
	public int insertTossHistory(TossResponseDTO responseDTO, int principalId) {

		int result = 0;

		TossHistoryDTO historyDTO = TossHistoryDTO.builder().paymentKey(responseDTO.getPaymentKey()).userId(principalId)
				.orderId(responseDTO.getOrderId()).orderName(responseDTO.getOrderName())
				.amount(responseDTO.getTotalAmount()).approvedAt(responseDTO.getApprovedAt()).build();

		result = historyRepository.insertTossHistory(historyDTO);

		if (result != 1) {
			throw new RuntimeException("결제 처리 실패");
		}
		return result;
	}

	/**
	 * 결제 취소
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Transactional
	public String cancelPayment(int id) throws IOException, InterruptedException {

		TossHistoryDTO historyDTO = historyRepository.searchPayment(id);

		String uri = "https://api.tosspayments.com/v1/payments/" + historyDTO.getPaymentKey() + "/cancel";
		HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).header("Authorization",
				"Basic dGVzdF9ja180eUtlcTViZ3JwejVreDUwUE45NDNHWDBselc2OnRlc3Rfc2tfR3Y2TGplS0Q4YVBCMTJhSnFRZWVyd1l4QWRYeQ==")
				.header("Content-Type", "application/json")
				.method("POST", HttpRequest.BodyPublishers.ofString("{\"cancelReason\":\"고객이 취소를 요청함\"}")).build();

		HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest,
				HttpResponse.BodyHandlers.ofString());

		historyRepository.cancelPaymentHistory(historyDTO);

		historyRepository.cancelPayment(historyDTO.getPaymentKey(), historyDTO.getOrderId());

		return response.body();

	}

	/**
	 * 고유 주믄Id (orderId) 생성
	 * 
	 * @return 랜덤
	 */
	public String getOrderId() {
		return UUID.randomUUID().toString();
	}

//    /**
//     * 고유 주믄Id (orderId) 생성
//     * @return
//     */
//    public String getOrderId(){
//        // 현재날짜 + 시간 가져오기
//        Calendar calendar = Calendar.getInstance();
//        int y = calendar.get(Calendar.YEAR);
//        int m = calendar.get(Calendar.MONTH) + 1;  // 현재 월 (0부터 시작 ->  +1)
//        int d = calendar.get(Calendar.DATE);
//
//        // 랜덤 숫자생성
//        Random rd1 = new Random();
//        Random rd2 = new Random();
//        int rd11 = rd1.nextInt(100); // 0-99 까지 무작위 정수
//        int rd22 = rd2.nextInt(100); // 0-99 까지 무작위 정수
//
//        // 연도/월/일 정수값을 문자열로 변환 (year +String)
//        String yStr = Integer.toString(y);
//        String mStr = Integer.toString(m);
//        String dStr = Integer.toString(d);
//        String rd1Str = Integer.toString(rd11); // 첫 번째 무작위 숫자를 문자열로 변환
//        String rd2Str = Integer.toString(rd22); // 두 번째 무작위 숫자를 문자열로 변환
//
//        return mStr + rd1Str + yStr + rd2Str + dStr;
//
//    }

}
