import sys
input = sys.stdin.readline

def put(sticker):
    sr, sc = len(sticker), len(sticker[0])

    for x in range(n - sr + 1):
        for y in range(m - sc + 1):
            if compare(x, y, sr, sc, sticker):
                for sx in range(sr):
                    for sy in range(sc):
                        laptop[x + sx][y + sy] += sticker[sx][sy]
                return True

    return False

def compare(x, y, sr, sc, sticker):
    for sx in range(sr):
        for sy in range(sc):
            if laptop[x + sx][y + sy] == sticker[sx][sy] == 1:
                return False

    return True
    
for sticker in stickers:
    for i in range(4):
        if put(sticker):
            break
        sticker = rotate(sticker)

cnt = sum(map(sum, laptop))

print(cnt)