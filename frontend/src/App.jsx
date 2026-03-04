import './App.css'
import {useEffect, useState} from "react";

function App(){
    const [tips, setTips] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPage, setTotalPage] = useState(0);

    useEffect(() => {
        // URL을 /api/findAll 대신 /api/tips로 변경
        fetch(`http://localhost:8080/api/tips?page=${page}&size=12`)
            .then(res => {
                if (!res.ok) {
                    throw new Error("네트워크 응답이 올바르지 않습니다.");
                }
                return res.json();
            })
            .then(data => {
                setTips(data.content);
                setTotalPage(data.totalPages);
            })
            .catch(err => console.log("데이터 로드 실패:", err));
    }, [page]);

    return(
        <div className="container"> {/* 전체를 감싸는 컨테이너 추가 */}
            <h1 className="title">오늘의 인생 꿀팁 💡</h1>

            <ul className="tip-list">
                {tips.map(tip => (
                    <li key={tip.id} className="tip-card">
                        <div className="tip-number">#{tip.id}</div>
                        <div className="tip-content">{tip.tip}</div>
                    </li>
                ))}
            </ul>

            <div className="pagination"> {/* 버튼을 감싸는 div */}
                <button
                    onClick={() => setPage(page - 1)}
                    disabled={page === 0}
                >
                    이전
                </button>

                <span className="page-info"> {page + 1} / {totalPage} </span>

                <button
                    onClick={() => setPage(page + 1)}
                    disabled={page + 1 >= totalPage}
                >
                    다음
                </button>
            </div>
        </div>
    );
}

export default App;