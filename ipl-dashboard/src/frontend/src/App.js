import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { TeamApp } from './pages/TeamPage';

function App() {
  return (
    <div className="App">
      <Router>
        <Route path="/teams/:teamName">
          <TeamApp />
        </Route>
      </Router>
    </div>
  );
}

export default App;
