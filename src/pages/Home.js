import React,{useState} from 'react'
import {Data} from '../data'
import DatePicker from "react-datepicker";
import * as ImIcons from 'react-icons/im';

import "react-datepicker/dist/react-datepicker.css";


function Home() {
    const [searchTerm,setSearchTerm]=useState("");
    const [startDate, setStartDate] = useState(new Date());
    const [dataList,setDataList]=useState(Data);

    const [name,setName] = useState("");
    const [attending,setAttending] = useState('');
    const [selectDate,setSelectedDate] = useState(new Date());
    const [startTime,setStartTime]= useState("");
    const [endTime,setEndTime]= useState("");

    const addData=()=>{
        if(!(name=="" || attending=="" || startTime=="" || endTime=="")){
            setDataList([...dataList, {
                SlNo: dataList.length,
                Name:name,
                Attending:attending,
                Date:'04/22/2021',
                Start_time:startTime,
                End_time:endTime
        
            } ])
            setName('');
            setAttending('');
            setStartTime('');
            setEndTime('');
        }
    }
    const removefromlist = (deletePostId) => {
        let rows = [...dataList]
        rows.splice(deletePostId, 1)
        setDataList(rows)
      }
    return (
        <>
            <div className='home'>
                Home
            </div>
            <div className='paper'>
                <input 
                 className='otherinput'
                 type='text'
                 placeholder= "Search name.."
                 onChange={
                     (event)=>{
                         setSearchTerm(event.target.value);
                     }
                 }
                 />
                 From:
                 <DatePicker selected={startDate}  className='otherinput' onChange={date => setStartDate(date)} />
                 To:
                 <DatePicker selected={startDate}  className='otherinput' onChange={date => setStartDate(date)} />
            </div>
            <div className='paper'>
                <table>
                    <tr>
                        <th>Sl. no.</th>
                        <th>Meeting Name</th>
                        <th>No of People attending</th>
                        <th>Date</th>
                        <th>Start time</th>
                        <th>End time</th>
                        <th>Action</th>
                    </tr>
                    {dataList.filter(
                        (val)=>{
                            if(searchTerm==""){
                                return val;
                            }else if(val.Name.toLowerCase().includes(searchTerm.toLowerCase())){
                                return val;
                            }
                        }
                    )
                    .map((item,index)=>{
                        return (
                            <tr>
                                <td key={index}>{item.SlNo}</td>
                                <td key={index}>{item.Name}</td>
                                <td key={index}>{item.Attending}</td>
                                <td key={index}>{item.Date}</td>
                                <td key={index}>{item.Start_time}</td>
                                <td key={index}>{item.End_time}</td>
                                <td key={index} ><a onClick={i => removefromlist(i.SlNo)}><ImIcons.ImBin size={30} style={{ fill: 'red' }} /></a></td>
                            </tr>
                        )
                    })}
                </table>
                <div>
                    <table>
                        {/* <td><input placeholder="Enter sl no"  className='addinput' onChange={(event)=>{setSlNo(event.target.value)}}/></td> */}
                        <td><input placeholder="Enter Name"   className='addinput' onChange={(event)=>{setName(event.target.value)}}/></td>
                        <td><input placeholder="Enter Attending"  className='addinput' onChange={(event)=>{setAttending(event.target.value)}}/></td>
                         <td><DatePicker selected={selectDate}   className='addinput' onChange={(date) => {setSelectedDate(date)}}/></td> 
                        <td><input placeholder="Enter Start time"  className='addinput' onChange={(event)=>{setStartTime(event.target.value)}}/></td>
                        <td><input placeholder="Enter End Time"  className='addinput' onChange={(event)=>{setEndTime(event.target.value)}}/></td>
                        <td><button onClick={addData}>Add</button></td>
                    </table>
                    
                </div>
            </div>
            
        </>
    )
}

export default Home
