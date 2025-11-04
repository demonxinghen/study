#!/bin/bash

APP_KEYWORD="major-customer-backend"

# 1️⃣ 查找包含指定关键字的 Java 进程 PID
PID=$(jps -l | grep "$APP_KEYWORD" | grep -v "Jps" | awk '{print $1}')

if [ -z "$PID" ]; then
  echo "❌ 未找到包含 \"$APP_KEYWORD\" 的 Java 进程。"
  exit 1
fi

PID_COUNT=$(echo "$PID" | wc -l)
if [ "$PID_COUNT" -ne 1 ]; then
  echo "⚠️  找到多个包含 \"$APP_KEYWORD\" 的进程，请手动指定："
  jps -l | grep "$APP_KEYWORD"
  exit 1
fi

echo "✅ 目标进程: $APP_KEYWORD (PID=$PID)"
echo

# 2️⃣ 获取CPU占用最高的前4个线程
THREAD_INFO=$(top -Hp $PID -b -n 1 | awk 'NR>7 {print $1,$9}' | sort -k2 -nr | head -n 4)

if [ -z "$THREAD_INFO" ]; then
  echo "⚠️  未找到线程信息，请检查top权限或PID是否正确。"
  exit 1
fi

echo "🔥 CPU占用最高的4个线程:"
printf "%-10s %-10s %-10s\n" "线程ID" "CPU(%)" "16进制TID"
echo "-----------------------------------"

# 保存线程十六进制ID
TOP_THREADS=()

# 3️⃣ 输出线程信息并转换为十六进制
while read -r tid cpu; do
  hex=$(printf "%x\n" $tid)
  printf "%-10s %-10s 0x%s\n" "$tid" "$cpu" "$hex"
  TOP_THREADS+=($hex)
done <<< "$THREAD_INFO"

# 4️⃣ 导出线程堆栈
DUMP_FILE="thread_dump_$(date +%Y%m%d_%H%M%S).log"
echo
echo "📦 正在导出线程堆栈到 $DUMP_FILE ..."
jstack $PID > "$DUMP_FILE"

# 5️⃣ 输出堆栈摘要
echo
echo "=============================="
echo "📋 前4个高CPU线程堆栈摘要"
echo "=============================="
for h in "${TOP_THREADS[@]}"; do
  echo
  echo "------------------------------------"
  echo "🧵 线程ID (hex): 0x$h"
  echo "------------------------------------"
  awk "/nid=0x$h /,/^$/" "$DUMP_FILE"
  echo
done

echo "✅ 分析完成。完整堆栈已保存到 $DUMP_FILE"

