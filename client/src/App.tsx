import ChatContainer from './containers/ChatContainer';
import LogIn from './containers/LogIn'; 
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LogIn />} />
        <Route path="/dashboard" element={<ChatContainer/>}></Route>
        {/* Other routes */}
      </Routes>
    </Router>
  );
};


export default App
