const { Button, Table, Input } = antd;

const url = "http://localhost:9000/album/queryAlbumList"

function MyApp() {
  const [count, setCount] = React.useState(0);
  const [data, setData] = React.useState([]);
  const [name, setName] = React.useState('');

  React.useEffect(() => {
    axios.post(url, {
      params: {
        name,
      },
    }).then(r => {
      if (r.data.success) {
        setData(r.data.data);
      }
    });
  }, []);

  const fetchData = _.debounce(() => {
    axios.post(url, {
      params: {
        albumName: name,
      },
    }).then(r => {
      if (r.data.success) {
        setData(r.data.data);
      }
    })
  }, 500);

  const onChange = (e) => {
    setName(e.target.value);
    fetchData();
  }

  const columns = [
    {
      title: 'id',
      dataIndex: 'id',
    },
    {
      title: '专辑名称',
      dataIndex: 'name',
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      hideInSearch: true,
      hideInForm: true,
    },
  ];

  return (
    <React.Fragment>
      <div>
        <Input style={{ width: 500, margin: 10 }} onChange={onChange} value={name} allowClear />
      </div>
      <Button style={{ margin: 10 }} type="primary" onClick={() => setCount(count + 1)}>当前：{count}</Button>
      <Table rowKey="id" columns={columns} dataSource={data} />
    </React.Fragment>
  );
}

const container = document.getElementById('root');
const root = ReactDOM.createRoot(container);
root.render(<MyApp />);
