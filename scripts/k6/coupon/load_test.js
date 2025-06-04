import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 1000, // 동시 1000명
  duration: '1m', // 1분 동안 유지
};

export default function () {
  const userId = Math.floor(Math.random() * 1000000) + 1;
  const url = `http://host.docker.internal:8080/api/v1/users/${userId}/coupons/publish`;
  const payload = JSON.stringify({
    couponId: 1234,
  });
  const params = { headers: { 'Content-Type': 'application/json' } };

  http.post(url, payload, params);
  sleep(1);
}
