import './App.css';
import NavBar from './components/NavBar';
import {BrowserRouter as Router,Switch,Route} from 'react-router-dom'
import { IconContext } from 'react-icons';
import Home from './pages/Home';
import AboutMe from './pages/AboutMe';

function App() {
  return (
    <>
    <IconContext.Provider value={{ color: '#fff' }}>
    <Router>
      <NavBar/>
      <Switch>
          <Route path='/' exact component={Home}/>     
          <Route path='/about' component={AboutMe}/>       
      </Switch>
    </Router>
    </IconContext.Provider>
     
    </>
  );
}

export default App;
