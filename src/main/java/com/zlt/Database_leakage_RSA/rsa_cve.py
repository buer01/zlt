from Crypto.Util.number import *
import socketserver

welcome = b'''class Task(socketserver.BaseRequestHandler):
    def _recvall(self):
        BUFF_SIZE = 2048
        data = b''
        while True:
            part = self.request.recv(BUFF_SIZE)
            data += part
            if len(part) < BUFF_SIZE:
                break
        return data.strip()

    def send(self, msg, newline=True):
        try:
            if newline:
                msg += b'\n'
            self.request.sendall(msg)
        except:
            pass

    def recv(self, prompt=b'[-] '):
        self.send(prompt, newline=False)
        return self._recvall()

    def special_rsa(self):
        kbits = 37
        abit = 62
        M = 962947420735983927056946215901134429196419130606213075415963491270
        while True:
            k = getRandomNBitInteger(kbits)
            a = getRandomNBitInteger(abit)
            p = k * M + pow(0x10001, a, M)
            if isPrime(p):
                break
        while True:
            l = getRandomNBitInteger(kbits)
            b = getRandomNBitInteger(abit)
            q = l * M + pow(0x10001, b, M)
            if isPrime(q):
                break
        n = p * q
        self.send(b'n = ' + str(n).encode())
        flag = open('flag.txt', 'rb').read()
        m = bytes_to_long(flag)
        c = pow(m, 0x10001, n)
        self.send(b'c = ' + str(c).encode())
        self.request.close()

    def handle(self):
        self.special_rsa()
'''

class Task(socketserver.BaseRequestHandler):
    def _recvall(self):
        BUFF_SIZE = 2048
        data = b''
        while True:
            part = self.request.recv(BUFF_SIZE)
            data += part
            if len(part) < BUFF_SIZE:
                break
        return data.strip()

    def send(self, msg, newline=True):
        try:
            if newline:
                msg += b'\n'
            self.request.sendall(msg)
        except:
            pass

    def recv(self, prompt=b'[-] '):
        self.send(prompt, newline=False)
        return self._recvall()

    def special_rsa(self):
        kbits = 37
        abit = 62
        M = 962947420735983927056946215901134429196419130606213075415963491270
        while True:
            k = getRandomNBitInteger(kbits)
            a = getRandomNBitInteger(abit)
            p = k * M + pow(0x10001, a, M)
            if isPrime(p):
                break
        while True:
            l = getRandomNBitInteger(kbits)
            b = getRandomNBitInteger(abit)
            q = l * M + pow(0x10001, b, M)
            if isPrime(q):
                break
        n = p * q
        self.send(b"This is CVE 2017-15361 of RSA. Can you break me and get the flag?")
        self.send(b"Give you the source code:\n")
        self.send(welcome)
        self.send(b'n = ' + str(n).encode())
        flag = open('flag.txt', 'rb').read()
        m = bytes_to_long(flag)
        c = pow(m, 0x10001, n)
        self.send(b'c = ' + str(c).encode())
        self.request.close()

    def handle(self):
        self.special_rsa()


class ThreadedServer(socketserver.ThreadingMixIn, socketserver.TCPServer):
    pass


class ForkedServer(socketserver.ThreadingMixIn, socketserver.TCPServer):
    pass


if __name__ == "__main__":
    HOST, PORT = '0.0.0.0', 9999

    print("HOST:POST " + HOST + ":" + str(PORT))
    server = ForkedServer((HOST, PORT), Task)
    server.allow_reuse_address = True
    server.serve_forever()
