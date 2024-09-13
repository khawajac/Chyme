import ChatContainer from './containers/ChatContainer';
import LogIn from './containers/LogIn'; 
import CreateAccount from './containers/CreateAccount'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LogIn />} />
        <Route path="/dashboard" element={<ChatContainer/>}></Route>
        <Route path="/register" element={<CreateAccount/>}/>
      </Routes>
    </Router>
  );
};


export default App
